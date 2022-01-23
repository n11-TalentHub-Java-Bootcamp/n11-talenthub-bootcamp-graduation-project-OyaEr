package com.example.n11talenthubbootcampgraduationprojectoyaer.converter;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientEntityConverter {

     ClientEntityConverter INSTANCE = Mappers.getMapper(ClientEntityConverter.class);

     List<ClientDto> convertAllClientListToClientDtoList (List<ClientEntity> clientEntityList);

     ClientEntity convertAllClientDtoListToClientList(ClientDto clientDto);

     ClientDto convertAllClientListToClientDtoList (ClientEntity clientEntity);

     List<ClientResponseDto> convertAllClientListToClientResponseDtoList (List<ClientEntity> clientEntityList);

     ClientEntity convertAllClientResponseDtoListToClientList(ClientResponseDto clientResponseDto);

     List<ClientRequestDto> convertAllClientListToClientRequestDtoList (List<ClientEntity> clientEntityList);

     ClientEntity convertAllClientRequestDtoListToClientList(ClientRequestDto clientRequestDto);

     List<CreditStatusDto> convertAllClientListToCreditStatusDtoList (List<ClientEntity> clientEntityList);

     ClientEntity convertAllCreditStatusDtoListToClientList(CreditStatusDto creditStatusDto);
}
