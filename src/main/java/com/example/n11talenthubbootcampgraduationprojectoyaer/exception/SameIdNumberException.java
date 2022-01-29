package com.example.n11talenthubbootcampgraduationprojectoyaer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SameIdNumberException extends RuntimeException {
    public  SameIdNumberException(String message){
         super(message);
    }
}
