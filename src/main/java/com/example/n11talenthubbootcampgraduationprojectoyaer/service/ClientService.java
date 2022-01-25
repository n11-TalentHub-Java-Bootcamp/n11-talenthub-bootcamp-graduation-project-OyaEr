package com.example.n11talenthubbootcampgraduationprojectoyaer.service;

import com.example.n11talenthubbootcampgraduationprojectoyaer.converter.ClientEntityConverter;
import com.example.n11talenthubbootcampgraduationprojectoyaer.creditApplicationStrategy.*;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.ClientDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CreditApplicationInfoDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.ClientDoesNotExist;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.IDNumberDoesNotExistException;
import com.example.n11talenthubbootcampgraduationprojectoyaer.exception.SameIdNumberException;
import com.example.n11talenthubbootcampgraduationprojectoyaer.util.CreditScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@Configurable
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private CreditScore creditScore;

    @Autowired
    private CreditApplicationInfoDao infoDao;



    //strategy class
    private CreditApplication creditApplication;


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
            CreditApplicationInfoEntity infoEntity = creditApprove(clientEntity.getCreditScore(),clientEntity.getIncome(),clientEntity.getAssurance(),clientEntity);

            infoDao.save(infoEntity);
            return clientDto;
        }

    }

    public CreditApplicationInfoEntity creditApprove(int creditScore, BigDecimal income,BigDecimal assurance, ClientEntity clientEntity){
        if(creditScore<500){
            setCreditApplicationStrategy(new ConcreteCreditApplicationCase1());
        }
        if( (500<= creditScore && creditScore<1000) && income.compareTo(new BigDecimal(5000))<0){
            setCreditApplicationStrategy(new ConcreteCreditApplicationCase2());
        }
        if((500<= creditScore && creditScore<1000 )&& (income.compareTo(new BigDecimal(5000))>=0 && income.compareTo(new BigDecimal(10000)) <0)){
            setCreditApplicationStrategy(new ConcreteCreditApplicationCase3());
        }
        if((500<= creditScore && creditScore<1000) && income.compareTo(new BigDecimal(10000))>=0){
            setCreditApplicationStrategy(new ConcreteCreditApplicationCase4());
        }
        if( creditScore>=1000 ){
            setCreditApplicationStrategy(new ConcreteCreditApplicationCase5());
        }

        return executeCreditApplicationStrategy(creditScore,income,assurance,clientEntity);

    }

    public ClientDto updateClientInfo(String idNum, ClientRequestDto clientRequestDto) {

        boolean isClientExist= isClientExist(idNum);
        ClientEntity clientEntity = clientDao.findByIdNum(idNum);
        Long clientId= clientEntity.getId();
        List<CreditApplicationInfoEntity> clientApproveList = infoDao.findByClientId(clientId);

        if(isClientExist){
            clientEntity.setAssurance(clientRequestDto.getAssurance());
            clientEntity.setIncome(clientRequestDto.getIncome());
            clientEntity.setPhoneNum(clientRequestDto.getPhoneNum());
            clientEntity.setCreditScore(creditScore.calculateCreditScore(clientEntity));

            for (CreditApplicationInfoEntity infoEntity : clientApproveList) {
                if(infoEntity.getCreditStatus().equals("ONAY")){
                    System.out.println("Onaylı başvuru var, başvuru yapılamaz.");
                }
            }

            CreditApplicationInfoEntity creditInfo =creditApprove(clientEntity.getCreditScore(),clientEntity.getIncome(),clientEntity.getAssurance(),clientEntity);
            infoDao.save(creditInfo);

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

    public void setCreditApplicationStrategy(CreditApplication creditApplication) {
        this.creditApplication = creditApplication;
    }

    public CreditApplicationInfoEntity executeCreditApplicationStrategy(int creditScore, BigDecimal income,BigDecimal assurance, ClientEntity clientEntity){
        return creditApplication.creditApproval(creditScore,income,assurance,clientEntity);
    }
}
