package com.example.n11talenthubbootcampgraduationprojectoyaer.controller;


import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@CrossOrigin
@Tag(name = "Customer Controller", description = "Here we can list, save, delete and update customers.")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("")
    @Operation(summary = "All customers are listed.")
    public ResponseEntity<CustomerDto> saveCustomers(@RequestBody CustomerDto customerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomers(customerDto));
    }


    @PutMapping("{idNum}")
    @Operation(summary = "All customers are listed.")
    public ResponseEntity<CustomerDto> updateCustomerInfo (@PathVariable String idNum , @RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerInfo(idNum, customerRequestDto));
    }

    @DeleteMapping("{idNum}")
    @Operation(summary = "All customers are listed.")
    public ResponseEntity<String> deleteCustomer (@PathVariable String idNum ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.deleteCustomerByIdNum(idNum));
    }

}
