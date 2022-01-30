package com.example.n11talenthubbootcampgraduationprojectoyaer.service;

import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.CreditApplicationInfoConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CustomerDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.CreditStatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CreditApplicationInfoService {

    private final CustomerDao customerDao;


    private final CreditApplicationInfoDao infoDao;


    private final ValidateService validateService;


    public List<CreditStatusResponseDto> getCreditStatus(CreditStatusDto creditStatusDto) {

        log.info("A request to view credit results has been received.");

        String idNum= creditStatusDto.getIdNum();
        LocalDate birthDate= creditStatusDto.getBirthDate();


        Optional<Customer> optionalCustomer= customerDao.findByIdNum(idNum);
        Customer customer = validateService.validateExistingCustomer(optionalCustomer);

        Date birth= customer.getBirthDate();
        LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(birth) );

        validateService.validateIdNumAndBirthDateMatch(localDate,birthDate);

        List<CreditApplicationInfo> creditApplicationInfoList = infoDao.findByCustomerId(customer.getId());

        List<CreditStatusResponseDto> responseDto = CreditApplicationInfoConverter.INSTANCE.convertAllCreditApplicationInfoListToCreditStatusResponseDtoList(creditApplicationInfoList);

        for (CreditApplicationInfo infoEntity : creditApplicationInfoList) {

            if(infoEntity.getCreditStatus().equals(CreditStatusType.ONAY.getCreditStatus())){
                return responseDto;
            }

        }
        return responseDto;
    }

    public void sendSms(Customer customer, CreditApplicationInfo info) {

        String phoneNumber= customer.getPhoneNum();
        String creditStatus= info.getCreditStatus();
        BigDecimal creditLimit=info.getCreditLimit();

        log.info("Kredi Durum: " + creditStatus + " " + "Kredi Limit: " + creditLimit + ", " + phoneNumber + " telefon numaralı müşteriye gönderildi.");

    }
}
