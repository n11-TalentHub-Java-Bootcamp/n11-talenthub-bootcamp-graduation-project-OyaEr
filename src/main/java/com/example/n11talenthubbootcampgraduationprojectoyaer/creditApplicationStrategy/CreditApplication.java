package com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface CreditApplication {

    public CreditApplicationInfo creditApproval(int creditScore, BigDecimal income, BigDecimal assurance, Customer customer);
}
