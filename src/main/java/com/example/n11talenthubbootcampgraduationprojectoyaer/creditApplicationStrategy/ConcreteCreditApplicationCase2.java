package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;


import java.math.BigDecimal;
import java.util.Date;


public class ConcreteCreditApplicationCase2 implements CreditApplication{

    @Override
    public CreditApplicationInfo creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer) {
        CreditApplicationInfo customerInfo = new CreditApplicationInfo();
        BigDecimal creditLimit = new BigDecimal(10000);
        BigDecimal assuranceResult = new BigDecimal(0);

        if(!(assurance.compareTo(new BigDecimal(0)) == 0)){

            assuranceResult = assuranceResult.add(assurance.multiply(new BigDecimal(10)).divide(new BigDecimal(100)));

            creditLimit = creditLimit.add(assuranceResult);

            customerInfo.setCustomer(customer);
            customerInfo.setApplicationDate(new Date());
            customerInfo.setCreditLimit(creditLimit);
            customerInfo.setCreditStatus("ONAY");

            return customerInfo;
        }

        else{
            customerInfo.setCustomer(customer);
            customerInfo.setApplicationDate(new Date());
            customerInfo.setCreditLimit(creditLimit);
            customerInfo.setCreditStatus("ONAY");

            return customerInfo;

        }
    }
}
