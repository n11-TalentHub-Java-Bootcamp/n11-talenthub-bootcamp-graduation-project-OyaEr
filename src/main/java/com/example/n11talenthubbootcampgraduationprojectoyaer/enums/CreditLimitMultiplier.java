package com.example.n11talenthubbootcampgraduationprojectoyaer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum CreditLimitMultiplier {

    FOUR(4),
    TWO(2);

    private int limitMultiplier;
}
