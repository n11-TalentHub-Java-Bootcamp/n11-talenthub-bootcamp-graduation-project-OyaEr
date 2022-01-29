package com.example.n11talenthubbootcampgraduationprojectoyaer.service;

import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.CustomerConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy.*;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CustomerDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.CreditStatusType;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.Exceptions;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.*;
import com.example.n11talenthubbootcampgraduationprojectoyaer.util.CreditScore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@Configurable
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CreditScore creditScore;

    @Autowired
    private CreditApplicationInfoDao infoDao;

    @Autowired
    private CreditApplicationInfoService infoService;


    private CreditApplication creditApplication;



    public CustomerDto saveCustomers(CustomerDto customerDto) {

        log.info("Customer registration request received.");
        Customer customer = CustomerConverter.INSTANCE.convertAllCustomerDtoListToCustomerList(customerDto);

        boolean isCustomerExist= isCustomerExist(customer.getIdNum());
        boolean isPhoneNumberExist = isPhoneNumberExist(customer.getPhoneNum());

        if(isCustomerExist){
            log.error("This customer ID Number " + customer.getIdNum() + " already exists.");
            throw  new SameIdNumberException(Exceptions.SameIdNumberException.getMessage());
        }
        if(isPhoneNumberExist){
            log.error("This customer phone number " + customer.getPhoneNum() + " already exists.");
            throw new SamePhoneNumberException(Exceptions.SamePhoneNumberException.getMessage());
        }
        if(isPhoneNumberValid(customer.getPhoneNum())){
            customer.setCreditScore(creditScore.calculateCreditScore(customer));
            customerDao.save(customer);
            log.info("Customer saved.");

            log.info("Redirected to the method of saving applications and sending sms.");
            saveCreditApplicationAndInformCustomer(customer.getCreditScore(), customer.getIncome(), customer.getAssurance(), customer);
            return customerDto;
        }
        else{
            log.error("This customer phone number not valid.");
            throw new PhoneNumberNotValidException(Exceptions.PhoneNumberNotValidException.getMessage());
        }
    }

    private CreditApplicationInfo creditApprove(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer){
        if(creditScore<500){
            setCreditApplicationStrategy(new ConcreteCreditApplicationCase1());
        }
        if( (500<= creditScore && creditScore<1000) && income.compareTo(new BigDecimal(5000))<0){
            setCreditApplicationStrategy(new ConcreteCreditApplicationCase2());
        }
        if((500<= creditScore && creditScore<1000 )&& (income.compareTo(new BigDecimal(5000))>=0 && income.compareTo(new BigDecimal(10000)) <0)){
            setCreditApplicationStrategy(new ConcreteCreditApplicationCase3());
        }
        if((500<= creditScore && creditScore<1000) && income.compareTo(new BigDecimal(10000))>=0){
            setCreditApplicationStrategy(new ConcreteCreditApplicationCase4());
        }
        if( creditScore>=1000 ){
            setCreditApplicationStrategy(new ConcreteCreditApplicationCase5());
        }

        log.info("Credit limit strategies have been determined. The relevant method will be run.");
        return executeCreditApplicationStrategy(creditScore,income,assurance, customer);

    }

    public CustomerDto updateCustomerInfo(String idNum, CustomerRequestDto customerRequestDto) {

        log.info("Customer update request received.");

        boolean isCustomerExist= isCustomerExist(idNum);
        boolean isPhoneNumberExist = isPhoneNumberExist(customerRequestDto.getPhoneNum());
        Customer customer = customerDao.findByIdNum(idNum);
        Long customerId= customer.getId();
        String phoneNumber= customerRequestDto.getPhoneNum();
        List<CreditApplicationInfo> customerApproveList = infoDao.findByCustomerId(customerId);

        if(isCustomerExist){
            if(isPhoneNumberExist){

                log.error("This customer phone number " + customer.getPhoneNum() + " already exists.");
                throw new SamePhoneNumberException(Exceptions.SamePhoneNumberException.getMessage());
            }
            if(isPhoneNumberValid(phoneNumber)){
                customer.setAssurance(customerRequestDto.getAssurance());
                customer.setIncome(customerRequestDto.getIncome());
                customer.setPhoneNum(customerRequestDto.getPhoneNum());
                customer.setCreditScore(creditScore.calculateCreditScore(customer));

                for (CreditApplicationInfo infoEntity : customerApproveList) {
                    if(infoEntity.getCreditStatus().equals(CreditStatusType.ONAY.getCreditStatus())){
                        log.error("Have approved credit. Application cannot be made.");
                        throw new ApprovedApplicationException(Exceptions.ApprovedApplicationException.getMessage());
                    }
                }

                log.info("Redirected to the method of saving applications and sending sms.");
                saveCreditApplicationAndInformCustomer(customer.getCreditScore(), customer.getIncome(), customer.getAssurance(), customer);

                CustomerDto customerDto = CustomerConverter.INSTANCE.convertAllCustomerListToCustomerDtoList(customer);
                return customerDto;
            }

            log.error("This customer phone number not valid.");
            throw new PhoneNumberNotValidException(Exceptions.PhoneNumberNotValidException.getMessage());
        }
        else{
            log.error("This customer ID number does not exist.");
            throw  new IDNumberDoesNotExistException(Exceptions.IDNumberDoesNotExistException.getMessage());
        }
    }

    private void saveCreditApplicationAndInformCustomer(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer){

        CreditApplicationInfo creditInfo =creditApprove(customer.getCreditScore(), customer.getIncome(), customer.getAssurance(), customer);
        infoDao.save(creditInfo);
        infoService.sendSms(customer,creditInfo);

        log.info("Customer credit application saved. The sms sending method has been called.");
    }



    public String deleteCustomerByIdNum(String idNum) {

        log.info("Customer deletion request received.");

        boolean isCustomerExist= isCustomerExist(idNum);
        if(isCustomerExist){
            customerDao.deleteByIdNum(idNum);
            log.warn("The client's applications were also deleted.");
            return "Delete successfully.";
        }
        else{
            log.error("This customer ID number does not exist.");
            throw new CustomerDoesNotExistException(Exceptions.CustomerDoesNotExistException.getMessage());
        }
    }

    private boolean isCustomerExist(String idNum){

        Customer customer = customerDao.findByIdNum(idNum);

        if(customer !=null){
            return true;
        }
        return false;
    }

    private boolean isPhoneNumberValid(String phoneNumber){

        String firstLetter= phoneNumber.substring(0,1);
        String secondLetter= phoneNumber.substring(1,2);

        if(firstLetter.equals("0") && secondLetter.equals("5")){
            return true;
        }
        return false;
    }

    private boolean isPhoneNumberExist(String phoneNumber){
        Customer customer = customerDao.findByPhoneNum(phoneNumber);

        if(customer !=null){
            return true;
        }
        return false;
    }


    private void setCreditApplicationStrategy(CreditApplication creditApplication) {
        this.creditApplication = creditApplication;
    }

    private CreditApplicationInfo executeCreditApplicationStrategy(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer){
        return creditApplication.creditApproval(creditScore,income,assurance, customer);
    }
}
