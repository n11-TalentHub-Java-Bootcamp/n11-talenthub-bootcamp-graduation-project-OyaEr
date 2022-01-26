package com.example.n11talenthubbootcampgraduationprojectoyaer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Date errorDate;
    private String message;
    private String detail;
}
