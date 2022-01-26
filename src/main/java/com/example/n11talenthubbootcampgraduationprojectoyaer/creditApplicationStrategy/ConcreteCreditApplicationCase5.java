package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;


import java.math.BigDecimal;
import java.util.Date;


public class ConcreteCreditApplicationCase5 implements CreditApplication{

    private final int creditLimitMultiplier=4;


    @Override
    public CreditApplicationInfo creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer) {

        BigDecimal creditLimit = new BigDecimal(0);
        BigDecimal assuranceResult = new BigDecimal(0);

        if(!(assurance.compareTo(new BigDecimal(0)) == 0)){

            creditLimit= creditLimit.add(income.multiply(new BigDecimal(creditLimitMultiplier)));

            assuranceResult = assuranceResult.add(assurance.multiply(new BigDecimal(50)).divide(new BigDecimal(100)));

            creditLimit = creditLimit.add(assuranceResult);

            CreditApplicationInfo customerInfo = new CreditApplicationInfo();
            customerInfo.setCustomer(customer);
            customerInfo.setApplicationDate(new Date());
            customerInfo.setCreditLimit(creditLimit);
            customerInfo.setCreditStatus("ONAY");
            return customerInfo;
        }

        else{
            creditLimit =  creditLimit.add(income.multiply(new BigDecimal(creditLimitMultiplier)));
            CreditApplicationInfo customerInfo = new CreditApplicationInfo();
            customerInfo.setCustomer(customer);
            customerInfo.setApplicationDate(new Date());
            customerInfo.setCreditLimit(creditLimit);
            customerInfo.setCreditStatus("ONAY");
            return customerInfo;

        }

    }
}
