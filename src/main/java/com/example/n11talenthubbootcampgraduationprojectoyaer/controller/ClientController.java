package com.example.n11talenthubbootcampgraduationprojectoyaer.controller;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.ClientDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    ClientService clientService; //private final??

    @Autowired
    ClientDao clientDao;

    @PostMapping("")
    public ResponseEntity<ClientDto> saveClients(@RequestBody ClientDto clientDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClients(clientDto));
    }


    @GetMapping("/allClients")
    public ResponseEntity<List<ClientResponseDto>> getAllClients(){
        List<ClientResponseDto > clientResponseDtoList= clientService.getAllClients();
        return ResponseEntity.ok().body(clientResponseDtoList);
    }
}
