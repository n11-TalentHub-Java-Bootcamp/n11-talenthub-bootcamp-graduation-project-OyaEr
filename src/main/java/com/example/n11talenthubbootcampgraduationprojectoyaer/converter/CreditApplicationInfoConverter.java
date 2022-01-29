package com.example.n11talenthubbootcampgraduationprojectoyaer.converter;


import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditApplicationInfoConverter {

    CreditApplicationInfoConverter INSTANCE = Mappers.getMapper(CreditApplicationInfoConverter.class);

    CreditStatusResponseDto convertCreditApplicationInfoToCreditStatusResponseDto (CreditApplicationInfo info);

    List<CreditStatusResponseDto> convertAllCreditApplicationInfoListToCreditStatusResponseDtoList (List<CreditApplicationInfo> infoList);

    CreditApplicationInfo convertCreditStatusResponseDtoToCreditApplicationInfo(CreditStatusResponseDto responseDto);

}
