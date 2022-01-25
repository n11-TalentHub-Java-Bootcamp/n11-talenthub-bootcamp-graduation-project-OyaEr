package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

public class ConcreteCreditApplicationCase4 implements CreditApplication{

    private final int creditLimitMultiplier=2;

    @Override
    public CreditApplicationInfoEntity creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, ClientEntity clientEntity) {

        BigDecimal creditLimit = new BigDecimal(0);
        BigDecimal assuranceResult = new BigDecimal(0);

        if(!(assurance.compareTo(new BigDecimal(0)) == 0)){

            creditLimit = creditLimit.add(income.multiply(new BigDecimal(creditLimitMultiplier)));

            assuranceResult = assuranceResult.add(assurance.multiply(new BigDecimal(25)).divide(new BigDecimal(100)));

            creditLimit = creditLimit.add(assuranceResult);

            CreditApplicationInfoEntity clientInfo = new CreditApplicationInfoEntity();
            clientInfo.setClient(clientEntity);
            clientInfo.setApplicationDate(new Date());
            clientInfo.setCreditLimit(creditLimit);
            clientInfo.setCreditStatus("ONAY");
            return clientInfo;
        }

        else{
            creditLimit = creditLimit.add(income.multiply(new BigDecimal(creditLimitMultiplier)));
            CreditApplicationInfoEntity clientInfo = new CreditApplicationInfoEntity();
            clientInfo.setClient(clientEntity);
            clientInfo.setApplicationDate(new Date());
            clientInfo.setCreditLimit(creditLimit);
            clientInfo.setCreditStatus("ONAY");
            return clientInfo;
        }

    }
}
