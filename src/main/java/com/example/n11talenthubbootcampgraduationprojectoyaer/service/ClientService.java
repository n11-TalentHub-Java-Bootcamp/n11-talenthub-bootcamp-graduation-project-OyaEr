package com.example.n11talenthubbootcampgraduationprojectoyaer.service;


import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.ClientEntityConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.ClientDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.ClientDoesNotExist;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.IDNumberDoesNotExistException;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.SameIdNumberException;
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

        boolean isClientExist= isClientExist(clientEntity.getIdNum());

        if(isClientExist){
            throw  new SameIdNumberException("This client ID Number " + clientEntity.getIdNum() + "already exists.");
        }
        else{
            clientEntity.setCreditScore(creditScore.calculateCreditScore(clientEntity));
            clientDao.save(clientEntity);

            return clientDto;
        }

    }

    public ClientDto updateClientInfo(String idNum, ClientRequestDto clientRequestDto) {


        boolean isClientExist= isClientExist(idNum);
        ClientEntity clientEntity = clientDao.findByIdNum(idNum);

        if(isClientExist){
            clientEntity.setAssurance(clientRequestDto.getAssurance());
            clientEntity.setIncome(clientRequestDto.getIncome());
            clientEntity.setPhoneNum(clientRequestDto.getPhoneNum());
            ClientDto clientDto = ClientEntityConverter.INSTANCE.convertAllClientListToClientDtoList(clientEntity);

            return clientDto;
        }
        else{

            throw  new IDNumberDoesNotExistException("This client ID Number " + idNum + "not found");
        }
    }



    public String deleteClientByIdNum(String idNum) {

        boolean isClientExist= isClientExist(idNum);
        if(isClientExist){
            clientDao.deleteByIdNum(idNum);
            return "Delete successfully.";
        }
        else{
            throw new ClientDoesNotExist("This client not found");
        }
    }

    public boolean isClientExist(String idNum){

        ClientEntity clientEntity= clientDao.findByIdNum(idNum);

        if(clientEntity!=null){
            return true;
        }
        return false;
    }
}
