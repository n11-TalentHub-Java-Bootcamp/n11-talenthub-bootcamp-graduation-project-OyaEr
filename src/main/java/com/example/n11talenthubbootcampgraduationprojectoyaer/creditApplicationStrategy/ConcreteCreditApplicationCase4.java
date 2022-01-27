package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.CreditLimitMultiplier;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.CreditStatusType;

import java.math.BigDecimal;
import java.util.Date;

public class ConcreteCreditApplicationCase4 implements CreditApplication{


    @Override
    public CreditApplicationInfo creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer) {

        BigDecimal creditLimit = new BigDecimal(0);
        BigDecimal assuranceResult = new BigDecimal(0);

        if(!(assurance.compareTo(new BigDecimal(0)) == 0)){

            creditLimit = creditLimit.add(income.multiply(new BigDecimal(CreditLimitMultiplier.TWO.getLimitMultiplier())));

            assuranceResult = assuranceResult.add(assurance.multiply(new BigDecimal(25)).divide(new BigDecimal(100)));

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
            creditLimit = creditLimit.add(income.multiply(new BigDecimal(CreditLimitMultiplier.TWO.getLimitMultiplier())));

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
