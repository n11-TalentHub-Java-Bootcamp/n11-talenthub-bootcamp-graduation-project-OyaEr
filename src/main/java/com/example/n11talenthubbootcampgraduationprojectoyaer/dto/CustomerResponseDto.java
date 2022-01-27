package com.example.n11talenthubbootcampgraduationprojectoyaer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {

    private String fullName;
    private int creditScore;

}
