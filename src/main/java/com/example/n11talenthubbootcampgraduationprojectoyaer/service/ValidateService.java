package com.example.n11talenthubbootcampgraduationprojectoyaer.service;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.CreditStatusType;
import com.example.n11talenthubbootcampgraduationprojectoyaer.enums.Exceptions;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ValidateService {

    public void validateNewCustomer(Optional<Customer> optionalCustomer){
        if(optionalCustomer.isPresent()){
            log.error("This customer ID Number " + optionalCustomer.get().getIdNum() + " already exists.");
            throw new SameIdNumberException(Exceptions.SameIdNumberException.getMessage());
        }
    }

    public Customer validateExistingCustomer(Optional<Customer> optionalCustomer){

        if(optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }
        else{
            log.error("This customer ID number does not exist.");
            throw new CustomerDoesNotExistException(Exceptions.CustomerDoesNotExistException.getMessage());
        }
    }


    public void validatePhoneNumber(Optional<Customer> optionalCustomer){
        if(optionalCustomer.isPresent()){
            log.error("This customer phone number " + optionalCustomer.get().getPhoneNum() + " already exists.");
            throw new SamePhoneNumberException(Exceptions.SamePhoneNumberException.getMessage());
        }
    }

    public void isPhoneNumberValid(String phoneNumber){ //private

        String firstLetter= phoneNumber.substring(0,1);
        String secondLetter= phoneNumber.substring(1,2);

        if(firstLetter.equals("0") && secondLetter.equals("5")){
            return;
        }

        log.error("This customer phone number not valid.");
        throw new PhoneNumberNotValidException(Exceptions.PhoneNumberNotValidException.getMessage());
    }

    public void validateNoneApprovedCredit(List<CreditApplicationInfo> customerApproveList){

        for (CreditApplicationInfo infoEntity : customerApproveList) {
            if(infoEntity.getCreditStatus().equals(CreditStatusType.ONAY.getCreditStatus())){
                log.error("Have approved credit. Application cannot be made.");
                throw new ApprovedApplicationException(Exceptions.ApprovedApplicationException.getMessage());
            }
        }
    }

    public void validateIdNumAndBirthDateMatch(LocalDate customerBirthDate, LocalDate providedBirthDate){

        if(!customerBirthDate.isEqual(providedBirthDate)){
            log.error("Customer ID Number and birthdate not matched.");
            throw new IDNumberAndBirthDateNotMatchException(Exceptions.IDNumberAndBirthDateNotMatchException.getMessage());
        }
    }
}
