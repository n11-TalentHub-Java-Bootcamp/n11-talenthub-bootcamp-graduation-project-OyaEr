package com.example.n11talenthubbootcampgraduationprojectoyaer.service;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.ClientDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CreditApplicationInfoService {

    @Autowired
    ClientDao clientDao;

    @Autowired
    CreditApplicationInfoDao infoDao;

    public String getCreditStatus(CreditStatusDto creditStatusDto) {

        String idNum= creditStatusDto.getIdNum();
        LocalDate birthDate= creditStatusDto.getBirthDate();


        ClientEntity client= clientDao.findByIdNum(idNum);
        Date birth= client.getBirthDate();
        LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(birth) );


        if(localDate.isEqual(birthDate)){

            List<CreditApplicationInfoEntity> infoClient = infoDao.findByClientId(client.getId());

            for (CreditApplicationInfoEntity infoEntity : infoClient) {

                if(infoEntity.getCreditStatus().equals("ONAY")){

                    return "Kredi Sonucu:" + infoEntity.getCreditStatus() +  "\r" +"Kredi Limiti:" + infoEntity.getCreditLimit();
                }
            }

            return "Kredi Sonucu: RED";
        }

        else {
            throw new RuntimeException("tckımlık no ve doğum tarihi uyuşmadı.");
        }

    }
}
