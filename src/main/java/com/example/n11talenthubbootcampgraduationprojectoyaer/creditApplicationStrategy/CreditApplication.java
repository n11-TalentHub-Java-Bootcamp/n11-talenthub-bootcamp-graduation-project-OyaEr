package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;

import java.math.BigDecimal;

public interface CreditApplication {

    public void creditApproval(int creditScore,BigDecimal income,BigDecimal assurance,ClientEntity client);
}
