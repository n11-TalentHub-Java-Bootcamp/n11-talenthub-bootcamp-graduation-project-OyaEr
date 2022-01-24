package com.example.n11talenthubbootcampgraduationprojectoyaer.service.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Transactional
public class ConcreteCreditApplicationCase1 implements CreditApplication{

    @Autowired
    CreditApplicationInfoDao infoDao;

    @Override
    public void creditApproval(int creditScore, BigDecimal income, BigDecimal assurance,Long clientEntityId) {

        CreditApplicationInfoEntity clientInfo=null;
        clientInfo.setApplicationDate(new Date());
        clientInfo.setCreditLimit(new BigDecimal(0));
        clientInfo.setCreditStatus("RED");

        infoDao.save(clientInfo);
    }
}
