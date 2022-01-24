package com.example.n11talenthubbootcampgraduationprojectoyaer.service.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;

import java.math.BigDecimal;

public interface CreditApplication {

    public void creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, Long clientEntityId);
}
