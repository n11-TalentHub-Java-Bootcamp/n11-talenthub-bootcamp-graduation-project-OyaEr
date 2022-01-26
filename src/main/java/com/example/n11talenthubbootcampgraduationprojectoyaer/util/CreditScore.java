package com.example.n11talenthubbootcampgraduationprojectoyaer.util;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class CreditScore {

        public  int calculateCreditScore(Customer customer){

            int creditScore=0;

            if(customer.getIncome().compareTo(new BigDecimal(3000)) < 0 ){

                creditScore=400;
            }

            if ((customer.getIncome().compareTo(new BigDecimal(3000)) >= 0) && (customer.getIncome().compareTo(new BigDecimal(5000))< 0)){

                creditScore=500;
            }

            if ((customer.getIncome().compareTo(new BigDecimal(5000)) >=0) && (customer.getIncome().compareTo(new BigDecimal(8000))<0)){

                creditScore=700;
            }

            if ((customer.getIncome().compareTo(new BigDecimal(8000)) >= 0)  &&(customer.getIncome().compareTo(new BigDecimal(10000))< 0)){

                creditScore=900;
            }

            if ((customer.getIncome().compareTo(new BigDecimal(10000))>=0) && (customer.getIncome().compareTo(new BigDecimal(30000))<0)){

                creditScore=950;
            }

            if ((customer.getIncome().compareTo(new BigDecimal(30000))>=0)){

                creditScore=1000;
            }

            return creditScore;
        }

}
