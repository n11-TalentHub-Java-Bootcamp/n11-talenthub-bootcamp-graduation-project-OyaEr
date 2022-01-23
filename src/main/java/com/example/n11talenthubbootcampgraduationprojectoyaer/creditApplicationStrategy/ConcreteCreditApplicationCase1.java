package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;

import java.math.BigDecimal;

public class ConcreteCreditApplicationCase1 implements CreditApplication{

    @Override
    public BigDecimal creditApproval(int creditScore, BigDecimal income) {
        System.out.println("Kredi Sonucu: Red");
        return null;
    }
}
