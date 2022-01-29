package com.example.n11talenthubbootcampgraduationprojectoyaer.controller;


import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/customers")
@CrossOrigin
@Tag(name = "Customer Controller", description = "We can save, update and delete customers.")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @Operation(summary = "Customers were registered and credit applications were made.")
    @PostMapping("")
    public ResponseEntity<CustomerDto> saveCustomers(@RequestBody CustomerDto customerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomers(customerDto));
    }


    @Operation(summary = "Customers were registered and credit applications were redone according to the updates.")
    @PutMapping("{idNum}")
    public ResponseEntity<CustomerDto> updateCustomerInfo (@PathVariable String idNum , @RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerInfo(idNum, customerRequestDto));
    }

    @Operation(summary = "Customers by id number have been deleted.")
    @DeleteMapping("{idNum}")
    public ResponseEntity<String> deleteCustomer (@PathVariable String idNum ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.deleteCustomerByIdNum(idNum));
    }

}
