package com.example.n11talenthubbootcampgraduationprojectoyaer.util;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class CreditScore {

        public  int calculateCreditScore(ClientEntity client){

            int creditScore=0;

            if(client.getIncome().compareTo(new BigDecimal(3000)) < 0 ){

                creditScore=400;
            }

            if ((client.getIncome().compareTo(new BigDecimal(3000)) >= 0) && (client.getIncome().compareTo(new BigDecimal(5000))< 0)){

                creditScore=500;
            }

            if ((client.getIncome().compareTo(new BigDecimal(5000)) >=0) && (client.getIncome().compareTo(new BigDecimal(8000))<0)){

                creditScore=700;
            }

            if ((client.getIncome().compareTo(new BigDecimal(8000)) >= 0)  &&(client.getIncome().compareTo(new BigDecimal(10000))< 0)){

                creditScore=900;
            }

            if ((client.getIncome().compareTo(new BigDecimal(10000))>=0) && (client.getIncome().compareTo(new BigDecimal(30000))<0)){

                creditScore=950;
            }

            if ((client.getIncome().compareTo(new BigDecimal(30000))>=0)){

                creditScore=1000;
            }

//            String idNum= client.getIdNum();
//
//            String threeDigit = idNum.substring(8);
//
//            int digits= Integer.parseInt(threeDigit);
//
//            int creditScore= digits +100;

            return creditScore;
        }

}
