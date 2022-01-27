package com.example.n11talenthubbootcampgraduationprojectoyaer.service;

import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.CustomerConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy.*;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CustomerDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.ApprovedApplicationException;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.CustomerDoesNotExistException;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.IDNumberDoesNotExistException;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.SameIdNumberException;
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



    private CreditApplication creditApplication;



    public CustomerDto saveCustomers(CustomerDto customerDto) {

        Customer customer = CustomerConverter.INSTANCE.convertAllCustomerDtoListToCustomerList(customerDto);

        boolean isCustomerExist= isCustomerExist(customer.getIdNum());

        if(isCustomerExist){
            throw  new SameIdNumberException("This customer ID Number " + customer.getIdNum() + " already exists.");
        }
        else{
            customer.setCreditScore(creditScore.calculateCreditScore(customer));
            customerDao.save(customer);
            CreditApplicationInfo infoEntity = creditApprove(customer.getCreditScore(), customer.getIncome(), customer.getAssurance(), customer);

            infoDao.save(infoEntity);
            return customerDto;
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

        return executeCreditApplicationStrategy(creditScore,income,assurance, customer);

    }

    public CustomerDto updateCustomerInfo(String idNum, CustomerRequestDto customerRequestDto) {

        boolean isCustomerExist= isCustomerExist(idNum);
        Customer customer = customerDao.findByIdNum(idNum);
        Long customerId= customer.getId();
        List<CreditApplicationInfo> customerApproveList = infoDao.findByCustomerId(customerId);

        if(isCustomerExist){
            customer.setAssurance(customerRequestDto.getAssurance());
            customer.setIncome(customerRequestDto.getIncome());
            customer.setPhoneNum(customerRequestDto.getPhoneNum());
            customer.setCreditScore(creditScore.calculateCreditScore(customer));

            for (CreditApplicationInfo infoEntity : customerApproveList) {
                if(infoEntity.getCreditStatus().equals("ONAY")){
                    throw new ApprovedApplicationException("There is an approved application.Can not apply.");
                }
            }

            CreditApplicationInfo creditInfo =creditApprove(customer.getCreditScore(), customer.getIncome(), customer.getAssurance(), customer);
            infoDao.save(creditInfo);

            CustomerDto customerDto = CustomerConverter.INSTANCE.convertAllCustomerListToCustomerDtoList(customer);
            return customerDto;
        }
        else{
            throw  new IDNumberDoesNotExistException("This customer ID Number " + idNum + "not found");
        }
    }



    public String deleteCustomerByIdNum(String idNum) {

        boolean isCustomerExist= isCustomerExist(idNum);
        if(isCustomerExist){
            customerDao.deleteByIdNum(idNum);
            return "Delete successfully.";
        }
        else{
            throw new CustomerDoesNotExistException("This customer not found.");
        }
    }

    private boolean isCustomerExist(String idNum){

        Customer customer = customerDao.findByIdNum(idNum);

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
