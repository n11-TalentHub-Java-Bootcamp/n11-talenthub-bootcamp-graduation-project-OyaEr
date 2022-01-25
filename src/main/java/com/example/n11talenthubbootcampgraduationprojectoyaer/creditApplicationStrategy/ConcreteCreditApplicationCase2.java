package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;


import java.math.BigDecimal;
import java.util.Date;


public class ConcreteCreditApplicationCase2 implements CreditApplication{

    @Override
    public CreditApplicationInfoEntity creditApproval(int creditScore, BigDecimal income,BigDecimal assurance, ClientEntity clientEntity) {
        CreditApplicationInfoEntity clientInfo = new CreditApplicationInfoEntity();
        BigDecimal creditLimit = new BigDecimal(10000);
        BigDecimal assuranceResult = new BigDecimal(0);

        if(!(assurance.compareTo(new BigDecimal(0)) == 0)){

            assuranceResult = assuranceResult.add(assurance.multiply(new BigDecimal(10)).divide(new BigDecimal(100)));

            creditLimit = creditLimit.add(assuranceResult);

            clientInfo.setClient(clientEntity);
            clientInfo.setApplicationDate(new Date());
            clientInfo.setCreditLimit(creditLimit);
            clientInfo.setCreditStatus("ONAY");

            return clientInfo;
        }

        else{
            clientInfo.setClient(clientEntity);
            clientInfo.setApplicationDate(new Date());
            clientInfo.setCreditLimit(creditLimit);
            clientInfo.setCreditStatus("ONAY");

            return clientInfo;

        }
    }
}
