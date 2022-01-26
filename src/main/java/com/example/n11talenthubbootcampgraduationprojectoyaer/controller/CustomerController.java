package com.example.n11talenthubbootcampgraduationprojectoyaer.controller;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.CustomerDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("")
    public ResponseEntity<CustomerDto> saveCustomers(@RequestBody CustomerDto customerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomers(customerDto));
    }


    @PutMapping("{idNum}")
    public ResponseEntity<CustomerDto> updateCustomerInfo (@PathVariable String idNum , @RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerInfo(idNum, customerRequestDto));
    }

    @DeleteMapping("{idNum}")
    public ResponseEntity<String> deleteCustomer (@PathVariable String idNum ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.deleteCustomerByIdNum(idNum));
    }

}
