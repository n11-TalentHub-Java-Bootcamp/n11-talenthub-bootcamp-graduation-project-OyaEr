package com.example.n11talenthubbootcampgraduationprojectoyaer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum Exceptions {

    ApprovedApplicationException("There is an approved application.Can not apply."),
    CustomerDoesNotExistException("This customer not found."),
    IDNumberAndBirthDateNotMatchException("ID number and date of birth did not match."),
    IDNumberDoesNotExistException("This customer ID Number not found."),
    PhoneNumberNotValidException("Phone number is not valid.Try again.."),
    SameIdNumberException("This customer ID Number already exists."),
    SamePhoneNumberException("This customer phone number already exists.");

    private String message;
}
