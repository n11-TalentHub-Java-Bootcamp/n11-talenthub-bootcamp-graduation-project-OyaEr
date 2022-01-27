package com.example.n11talenthubbootcampgraduationprojectoyaer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum CreditStatusType {

    ONAY("ONAY"),
    RED("RED");

    private String creditStatus;
}
