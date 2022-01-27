package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;


import java.math.BigDecimal;
import java.util.Date;


public class ConcreteCreditApplicationCase1 implements CreditApplication{

    //private CreditStatusType creditStatusType;

    @Override
    public CreditApplicationInfo creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer) {

        CreditApplicationInfo customerInfo = new CreditApplicationInfo();
        customerInfo.setCustomer(customer);
        customerInfo.setApplicationDate(new Date());
        customerInfo.setCreditLimit(new BigDecimal(0));
        customerInfo.setCreditStatus("RED");
        return customerInfo;
    }
}
