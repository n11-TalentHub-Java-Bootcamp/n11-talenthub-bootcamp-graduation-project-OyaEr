package com.example.n11talenthubbootcampgraduationprojectoyaer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientDoesNotExist extends RuntimeException{
    public ClientDoesNotExist(String message){
        super(message);
    }
}
