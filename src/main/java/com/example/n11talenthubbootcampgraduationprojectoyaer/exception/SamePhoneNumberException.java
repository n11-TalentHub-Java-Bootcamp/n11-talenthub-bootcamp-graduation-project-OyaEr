package com.example.n11talenthubbootcampgraduationprojectoyaer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SamePhoneNumberException extends  RuntimeException {
    public  SamePhoneNumberException(String message){
        super(message);
    }
}
