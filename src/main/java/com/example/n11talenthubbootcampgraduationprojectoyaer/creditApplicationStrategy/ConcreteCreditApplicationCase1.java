package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;


public class ConcreteCreditApplicationCase1 implements CreditApplication{


    @Override
    public CreditApplicationInfoEntity creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, ClientEntity clientEntity) {

        CreditApplicationInfoEntity clientInfo = new CreditApplicationInfoEntity();
        clientInfo.setClient(clientEntity);
        clientInfo.setApplicationDate(new Date());
        clientInfo.setCreditLimit(new BigDecimal(0));
        clientInfo.setCreditStatus("RED");
        return clientInfo;
    }
}
