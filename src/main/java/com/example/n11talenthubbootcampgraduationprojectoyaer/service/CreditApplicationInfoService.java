package com.example.n11talenthubbootcampgraduationprojectoyaer.service;

import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.ClientEntityConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.ClientDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Date birthDate= creditStatusDto.getBirthDate();

        ClientEntity client= clientDao.findByIdNum(idNum);

        if(client.getBirthDate().equals(birthDate)){

            List<CreditApplicationInfoEntity> infoClient = infoDao.findByClient(client);

            return "Kredi Sonucu: " + infoClient.get(0).getCreditStatus() + "Kredi Limiti: " + infoClient.get(0).getCreditLimit();

        }

        else {
            throw new RuntimeException("tckimlik no ve dogum t uyuşmadı.");
        }

    }
}
