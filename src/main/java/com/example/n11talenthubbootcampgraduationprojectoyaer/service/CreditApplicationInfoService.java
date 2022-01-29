package com.example.n11talenthubbootcampgraduationprojectoyaer.service;

import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.CreditApplicationInfoConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.CustomerConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CustomerDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.CreditStatusType;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.Exceptions;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.IDNumberAndBirthDateNotMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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


    public CreditStatusResponseDto getCreditStatus(CreditStatusDto creditStatusDto) {

        String idNum= creditStatusDto.getIdNum();
        LocalDate birthDate= creditStatusDto.getBirthDate();


        Customer customer= customerDao.findByIdNum(idNum);
        Date birth= customer.getBirthDate();
        LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(birth) );


        if(localDate.isEqual(birthDate)){

            List<CreditApplicationInfo> infoCustomer = infoDao.findByCustomerId(customer.getId());

            CreditStatusResponseDto responseDto = CreditApplicationInfoConverter.INSTANCE.convertAllCreditApplicationInfoListToCreditStatusResponseDtoList(infoCustomer.get(0));

            for (CreditApplicationInfo infoEntity : infoCustomer) {

                if(infoEntity.getCreditStatus().equals(CreditStatusType.ONAY.getCreditStatus())){

                    //return "Kredi Sonucu:" + infoEntity.getCreditStatus() +  "\r" +"Kredi Limiti:" + infoEntity.getCreditLimit();
                    return responseDto;
                }

            }
            return responseDto;
        }
        else {
            throw new IDNumberAndBirthDateNotMatchException(Exceptions.IDNumberAndBirthDateNotMatchException.getMessage());
        }

    }

    public void sendSms(Customer customer, CreditApplicationInfo info) {

        String phoneNumber= customer.getPhoneNum();
        String creditStatus= info.getCreditStatus();
        BigDecimal creditLimit=info.getCreditLimit();

        log.info("Kredi Sonucu: " + creditStatus + " " + "Kredi Limit: " + creditLimit + " " + phoneNumber + " numaralı kullanıcıya gönderildi.");

    }
}
