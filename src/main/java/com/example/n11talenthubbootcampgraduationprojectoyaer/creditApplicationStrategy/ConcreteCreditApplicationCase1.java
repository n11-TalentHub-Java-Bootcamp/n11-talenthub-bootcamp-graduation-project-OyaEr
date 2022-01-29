package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.CreditStatusType;
import lombok.extern.slf4j.Slf4j;


import java.math.BigDecimal;
import java.util.Date;

@Slf4j
public class ConcreteCreditApplicationCase1 implements CreditApplication{

    @Override
    public CreditApplicationInfo creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer) {
        CreditApplicationInfo customerInfo = CreditApplicationInfo.builder()
                .customer(customer)
                .applicationDate(new Date())
                .creditLimit(new BigDecimal (0))
                .creditStatus(CreditStatusType.RED.getCreditStatus())
                .build();
        log.info("Credit score below 500,case-1 run.");
        return customerInfo;
    }
}
