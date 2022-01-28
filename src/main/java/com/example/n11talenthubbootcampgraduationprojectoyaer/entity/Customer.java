package com.example.n11talenthubbootcampgraduationprojectoyaer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name= "CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue
    @Column(name="ID", nullable = false)
    private Long id;

    @Column(name="IDENTITY_NUMBER", nullable = false , length=11, updatable = false , unique = true)
    private String idNum;

    @Column (name= "NAME_SURNAME", nullable = false, length=100 )
    private String fullName;

    @Column(name = "MONTHLY_INCOME" , nullable = false, precision = 19, scale = 2)
    private BigDecimal income;

    @Column(name="PHONE_NUMBER", nullable = false, length=11, unique = true)
    private String phoneNum;

    @Column(name="BIRTH_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

    @Column(name="ASSURANCE",nullable = false, precision = 19, scale = 2)
    private BigDecimal assurance;

    @Column(name="CREDIT_SCORE" )
    private int creditScore;

}
