package com.example.n11talenthubbootcampgraduationprojectoyaer.service;

import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.CustomerConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy.*;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CustomerDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.util.CreditScore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Configurable
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDao customerDao;

    private final CreditScore creditScore;

    private final CreditApplicationInfoDao infoDao;

    private final CreditApplicationInfoService infoService;

    private final ValidateService validateService;

    private  CreditApplication creditApplication;


    public CustomerDto saveCustomers(CustomerDto customerDto) {

        log.info("Customer registration request received.");
        Customer customer = CustomerConverter.INSTANCE.convertAllCustomerDtoListToCustomerList(customerDto);

        Optional<Customer> optionalCustomer = customerDao.findByIdNum(customer.getIdNum());
        validateService.validateNewCustomer(optionalCustomer);

        Optional<Customer> customerOptional = customerDao.findByPhoneNum(customer.getPhoneNum());
        validateService.validatePhoneNumber(customerOptional);

        validateService.isPhoneNumberValid(customer.getPhoneNum());

        customer.setCreditScore(creditScore.calculateCreditScore(customer));
        customerDao.save(customer);
        log.info("Customer saved.");

        log.info("Redirected to the method of saving applications and sending sms.");
        saveCreditApplicationAndInformCustomer(customer.getCreditScore(), customer.getIncome(), customer.getAssurance(), customer);
        return customerDto;

    }

    public CreditApplicationInfo creditApprove(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer){
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


        Optional<Customer> optionalCustomer = customerDao.findByIdNum(idNum);
        Customer customer = validateService.validateExistingCustomer(optionalCustomer);

        Optional<Customer> customerOptional = customerDao.findByPhoneNum(customerRequestDto.getPhoneNum());
        validateService.validatePhoneNumber(customerOptional);

        Long customerId= customer.getId();
        String phoneNumber= customerRequestDto.getPhoneNum();
        List<CreditApplicationInfo> customerApproveList = infoDao.findByCustomerId(customerId);

        validateService.isPhoneNumberValid(phoneNumber);

        customer.setAssurance(customerRequestDto.getAssurance());
        customer.setIncome(customerRequestDto.getIncome());
        customer.setPhoneNum(customerRequestDto.getPhoneNum());
        customer.setCreditScore(creditScore.calculateCreditScore(customer));

        validateService.validateNoneApprovedCredit(customerApproveList);

        log.info("Redirected to the method of saving applications and sending sms.");
        saveCreditApplicationAndInformCustomer(customer.getCreditScore(), customer.getIncome(), customer.getAssurance(), customer);

        CustomerDto customerDto = CustomerConverter.INSTANCE.convertAllCustomerListToCustomerDtoList(customer);
        return customerDto;

    }

    private void saveCreditApplicationAndInformCustomer(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer){

        CreditApplicationInfo creditInfo =creditApprove(customer.getCreditScore(), customer.getIncome(), customer.getAssurance(), customer);
        infoDao.save(creditInfo);
        infoService.sendSms(customer,creditInfo);

        log.info("Customer credit application saved. The sms sending method has been called.");
    }



    public String deleteCustomerByIdNum(String idNum) {

        log.info("Customer deletion request received.");
        Optional<Customer> customerOptional = customerDao.findByIdNum(idNum);
        Customer customer = validateService.validateExistingCustomer(customerOptional);

        customerDao.deleteByIdNum(customer.getIdNum());
        log.warn("The client's applications were also deleted.");
        return "Delete successfully.";

    }

    public void setCreditApplicationStrategy(CreditApplication creditApplication) {
        this.creditApplication = creditApplication;
    }

    public CreditApplicationInfo executeCreditApplicationStrategy(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer){
        return creditApplication.creditApproval(creditScore,income,assurance, customer);
    }
}
