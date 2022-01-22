package com.example.n11talenthubbootcampgraduationprojectoyaer.service;


import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.ClientEntityConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.ClientDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.util.CreditScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {

    @Autowired
    ClientDao clientDao;

    @Autowired
    CreditScore creditScore;



    public List<ClientResponseDto> getAllClients(){
        List<ClientEntity> clientEntityList= clientDao.findAll();

        List <ClientResponseDto> clientResponseDtoList = ClientEntityConverter.INSTANCE.convertAllClientListToClientResponseDtoList(clientEntityList);

        return clientResponseDtoList;

    }

    public  ClientDto saveClients(ClientDto clientDto) {

        ClientEntity clientEntity = ClientEntityConverter.INSTANCE.convertAllClientDtoListToClientList(clientDto);

        clientEntity.setCreditScore(creditScore.calculateCreditScore(clientEntity));
        clientDao.save(clientEntity);

         return clientDto;
    }
}
