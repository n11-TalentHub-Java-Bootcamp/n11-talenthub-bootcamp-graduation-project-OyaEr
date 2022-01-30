package com.example.n11talenthubbootcampgraduationprojectoyaer.controller;


import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CustomerRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/customers")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Customer Controller", description = "We can save, update and delete customers.")
public class CustomerController {

    private final CustomerService customerService;


    @Operation(summary = "We can register client and apply for credit.")
    @PostMapping("")
    public ResponseEntity<CustomerDto> saveCustomers(@RequestBody CustomerDto customerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomers(customerDto));
    }


    @Operation(summary = "We can update customers and apply for credit according to the current situation.")
    @PutMapping("{idNum}")
    public ResponseEntity<CustomerDto> updateCustomerInfo (@PathVariable String idNum , @RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerInfo(idNum, customerRequestDto));
    }

    @Operation(summary = "We can delete customers with their id numbers.")
    @DeleteMapping("{idNum}")
    public ResponseEntity<String> deleteCustomer (@PathVariable String idNum ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.deleteCustomerByIdNum(idNum));
    }

}
