package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface CreditApplication {

    public CreditApplicationInfoEntity creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, ClientEntity clientEntity);
}
