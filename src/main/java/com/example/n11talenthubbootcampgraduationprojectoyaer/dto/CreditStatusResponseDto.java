package com.example.n11talenthubbootcampgraduationprojectoyaer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditStatusResponseDto {

    private BigDecimal creditLimit;
    private String creditStatus;

}
