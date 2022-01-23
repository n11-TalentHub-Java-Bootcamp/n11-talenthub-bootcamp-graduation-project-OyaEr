package com.example.n11talenthubbootcampgraduationprojectoyaer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditStatusDto {

    private String idNum;
    private Date birthDate;
}
