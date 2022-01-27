package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.CreditStatusType;


import java.math.BigDecimal;
import java.util.Date;


public class ConcreteCreditApplicationCase2 implements CreditApplication{

    @Override
    public CreditApplicationInfo creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer) {

        BigDecimal creditLimit = new BigDecimal(10000);
        BigDecimal assuranceResult = new BigDecimal(0);

        if(!(assurance.compareTo(new BigDecimal(0)) == 0)){

            assuranceResult = assuranceResult.add(assurance.multiply(new BigDecimal(10)).divide(new BigDecimal(100)));

            creditLimit = creditLimit.add(assuranceResult);

            CreditApplicationInfo customerInfo = CreditApplicationInfo.builder()
                    .customer(customer)
                    .applicationDate(new Date())
                    .creditLimit(creditLimit)
                    .creditStatus(CreditStatusType.ONAY.getCreditStatus())
                    .build();

            return customerInfo;
        }

        else{

            CreditApplicationInfo customerInfo = CreditApplicationInfo.builder()
                    .customer(customer)
                    .applicationDate(new Date())
                    .creditLimit(creditLimit)
                    .creditStatus(CreditStatusType.ONAY.getCreditStatus())
                    .build();

            return customerInfo;

        }
    }
}
