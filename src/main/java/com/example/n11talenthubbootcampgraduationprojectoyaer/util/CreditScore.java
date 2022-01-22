package com.example.n11talenthubbootcampgraduationprojectoyaer.util;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;

import org.springframework.stereotype.Component;


@Component
public class CreditScore {

        public  int calculateCreditScore(ClientEntity client){

            String idNum= client.getIdNum();

            String threeDigit = idNum.substring(8);

            int digits= Integer.parseInt(threeDigit);

            int creditScore= digits +100;

            return creditScore;
        }

}
