package com.example.n11talenthubbootcampgraduationprojectoyaer.service.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.CreditApplicationInfoConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditApplicationInfoDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Transactional
@Service
@Configurable
public class ConcreteCreditApplicationCase2 implements CreditApplication{

    @Autowired
    CreditApplicationInfoDao infoDao;



    @Override
    public void creditApproval(int creditScore, BigDecimal income,BigDecimal assurance, Long clientEntityId) {
        CreditApplicationInfoDto clientInfoDto = new CreditApplicationInfoDto();
        BigDecimal creditLimit = new BigDecimal(10000);
        BigDecimal assuranceResult = new BigDecimal(0);

        if(!(assurance.compareTo(new BigDecimal(0)) == 0)){

            assuranceResult = assuranceResult.add(assurance.multiply(new BigDecimal(10)).divide(new BigDecimal(100)));

            creditLimit = creditLimit.add(assuranceResult);

            clientInfoDto.setClientId(clientEntityId);
            clientInfoDto.setApplicationDate(new Date());
            clientInfoDto.setCreditLimit(creditLimit);
            clientInfoDto.setCreditStatus("ONAY");

            CreditApplicationInfoEntity creditApplicationInfo = CreditApplicationInfoConverter.INSTANCE.convertCreditApplicationInfoDtoToCreditApplicationInfo(clientInfoDto);
            System.out.println("anan");
            creditApplicationInfo.setId(31L);
            infoDao.save(creditApplicationInfo);

        }

        else{

            clientInfoDto.setClientId(clientEntityId);
            clientInfoDto.setApplicationDate(new Date());
            clientInfoDto.setCreditLimit(creditLimit);
            clientInfoDto.setCreditStatus("ONAY");

            CreditApplicationInfoEntity creditApplicationInfo = CreditApplicationInfoConverter.INSTANCE.convertCreditApplicationInfoDtoToCreditApplicationInfo(clientInfoDto);

            infoDao.save(creditApplicationInfo);

        }
    }
}
