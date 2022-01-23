package com.example.n11talenthubbootcampgraduationprojectoyaer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IDNumberDoesNotExistException extends RuntimeException {
    public IDNumberDoesNotExistException(String message){
        super(message);
    }
}
