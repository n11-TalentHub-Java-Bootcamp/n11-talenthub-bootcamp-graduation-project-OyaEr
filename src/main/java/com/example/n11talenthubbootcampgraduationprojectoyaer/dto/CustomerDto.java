package com.example.n11talenthubbootcampgraduationprojectoyaer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private String idNum;
    private String fullName;
    private BigDecimal income;
    private String phoneNum;
    private Date birthDate;
    private BigDecimal assurance;
}
