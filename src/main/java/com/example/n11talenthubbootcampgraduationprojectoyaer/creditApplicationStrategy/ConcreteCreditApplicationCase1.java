package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.CreditStatusType;


import java.math.BigDecimal;
import java.util.Date;


public class ConcreteCreditApplicationCase1 implements CreditApplication{

    @Override
    public CreditApplicationInfo creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer) {
        CreditApplicationInfo customerInfo = CreditApplicationInfo.builder()
                .customer(customer)
                .applicationDate(new Date())
                .creditLimit(new BigDecimal (0))
                .creditStatus(CreditStatusType.RED.getCreditStatus())
                .build();
        return customerInfo;
    }
}
