package com.example.n11talenthubbootcampgraduationprojectoyaer.converter;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerConverter {

    CustomerConverter INSTANCE = Mappers.getMapper(CustomerConverter.class);

    List<CustomerDto> convertAllCustomerListToCustomerDtoList (List<Customer> customerList);

    Customer convertAllCustomerDtoListToCustomerList(CustomerDto customerDto);

    CustomerDto convertAllCustomerListToCustomerDtoList (Customer customer);

    List<CustomerRequestDto> convertAllCustomerListToCustomerRequestDtoList (List<Customer> customerList);

    Customer convertAllCustomerRequestDtoListToCustomerList(CustomerRequestDto customerRequestDto);

    List<CreditStatusDto> convertAllCustomerListToCreditStatusDtoList (List<Customer> customerList);

    Customer convertAllCreditStatusDtoListToCustomerList(CreditStatusDto creditStatusDto);
}
