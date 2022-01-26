package com.example.n11talenthubbootcampgraduationprojectoyaer.service;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CustomerDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.IDNumberAndBirthDateNotMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CreditApplicationInfoService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CreditApplicationInfoDao infoDao;

//    private final SmsSender smsSender;
//
//    private final SmsRequest smsRequest;
//
//    @Autowired
//    public CreditApplicationInfoService(@Qualifier("twilio") TwilioSmsSender smsSender, SmsRequest smsRequest) {
//        this.smsSender = smsSender;
//        this.smsRequest = smsRequest;
//    }

    public String getCreditStatus(CreditStatusDto creditStatusDto) {

        String idNum= creditStatusDto.getIdNum();
        LocalDate birthDate= creditStatusDto.getBirthDate();


        Customer customer= customerDao.findByIdNum(idNum);
        Date birth= customer.getBirthDate();
        LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(birth) );


        if(localDate.isEqual(birthDate)){

            List<CreditApplicationInfo> infoCustomer = infoDao.findByCustomerId(customer.getId());

            for (CreditApplicationInfo infoEntity : infoCustomer) {

                if(infoEntity.getCreditStatus().equals("ONAY")){

                    return "Kredi Sonucu:" + infoEntity.getCreditStatus() +  "\r" +"Kredi Limiti:" + infoEntity.getCreditLimit();
                }

            }
            log.info("Kredi sonucu başarıyla görüntülendi.");
            return "Kredi Sonucu: RED";
        }
        else {
            throw new IDNumberAndBirthDateNotMatchException("ID number and date of birth did not match.");
        }

    }

//    public void sendSms(SmsRequest smsRequest) {
//
//        smsSender.sendSms(smsRequest);
//    }
}
