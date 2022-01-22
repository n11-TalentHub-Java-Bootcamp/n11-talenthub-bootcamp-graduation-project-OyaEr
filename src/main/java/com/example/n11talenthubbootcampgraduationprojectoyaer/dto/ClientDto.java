package com.example.n11talenthubbootcampgraduationprojectoyaer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String idNum;
    private String fullName;
    private BigDecimal income;
    private String phoneNum;
    private Date birthDate;
    private BigDecimal assurance;
}
