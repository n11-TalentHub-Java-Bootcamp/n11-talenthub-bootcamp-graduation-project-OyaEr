package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;

import java.math.BigDecimal;
import java.util.Date;

public class ConcreteCreditApplicationCase3 implements CreditApplication{

    CreditApplicationInfoDao infoDao;

    private final int creditLimitMultiplier=4; //BUNLAR STRATEGY INTERFACINDENGELİR Mİ??
    BigDecimal creditLimit = new BigDecimal(20000);
    BigDecimal assuranceResult = new BigDecimal(0);

    @Override
    public void creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, ClientEntity client) {

        if(!(assurance.compareTo(new BigDecimal(0)) == 0)){

            assuranceResult.add(assurance.multiply(new BigDecimal(20)).divide(new BigDecimal(100)));

            creditLimit.add(assuranceResult);

            CreditApplicationInfoEntity clientInfo=null;
            clientInfo.setClient(client);
            clientInfo.setApplicationDate(new Date());
            clientInfo.setCreditLimit(creditLimit);
            clientInfo.setCreditStatus("ONAY");

            infoDao.save(clientInfo);
        }

        else{
            CreditApplicationInfoEntity clientInfo=null;
            clientInfo.setClient(client);
            clientInfo.setApplicationDate(new Date());
            clientInfo.setCreditLimit(creditLimit);
            clientInfo.setCreditStatus("ONAY");

            infoDao.save(clientInfo);
        }

    }
}
