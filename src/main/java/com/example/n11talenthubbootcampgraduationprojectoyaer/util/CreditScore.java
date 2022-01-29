package com.example.n11talenthubbootcampgraduationprojectoyaer.util;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@Slf4j
public class CreditScore {

        public  int calculateCreditScore(Customer customer){

            int creditScore=0;

            if(customer.getIncome().compareTo(new BigDecimal(3000)) < 0 ){
                creditScore=400;
                log.info("Credit score assigned to 400");
            }

            if ((customer.getIncome().compareTo(new BigDecimal(3000)) >= 0) && (customer.getIncome().compareTo(new BigDecimal(5000))< 0)){
                creditScore=500;
                log.info("Credit score assigned to 500");
            }

            if ((customer.getIncome().compareTo(new BigDecimal(5000)) >=0) && (customer.getIncome().compareTo(new BigDecimal(8000))<0)){
                creditScore=700;
                log.info("Credit score assigned to 700");
            }

            if ((customer.getIncome().compareTo(new BigDecimal(8000)) >= 0)  &&(customer.getIncome().compareTo(new BigDecimal(10000))< 0)){
                creditScore=900;
                log.info("Credit score assigned to 900");
            }

            if ((customer.getIncome().compareTo(new BigDecimal(10000))>=0) && (customer.getIncome().compareTo(new BigDecimal(30000))<0)){
                creditScore=950;
                log.info("Credit score assigned to 950");
            }

            if ((customer.getIncome().compareTo(new BigDecimal(30000))>=0)){
                creditScore=1000;
                log.info("Credit score assigned to 1000");
            }

            return creditScore;
        }

}
