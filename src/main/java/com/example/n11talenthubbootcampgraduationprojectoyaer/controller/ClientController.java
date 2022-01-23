package com.example.n11talenthubbootcampgraduationprojectoyaer.controller;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dao.ClientDao;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientRequestDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.ClientResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clients")
@CrossOrigin
public class ClientController {

    @Autowired
    ClientService clientService; //private final??

    @Autowired
    ClientDao clientDao;

    @PostMapping("")
    public ResponseEntity<ClientDto> saveClients(@RequestBody ClientDto clientDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClients(clientDto));
    }


    //BURASI FULLNAME VE CREDİ SKORUNU DONUYO SU AN GÖRMEK İÇİN YAZDIM. 
    @GetMapping("/allClients")
    public ResponseEntity<List<ClientResponseDto>> getAllClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClients());
    }

    @PutMapping("{idNum}")
    public ResponseEntity<ClientDto> updateClientInfo (@PathVariable String idNum , @RequestBody ClientRequestDto clientRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClientInfo(idNum,clientRequestDto));
    }

    @DeleteMapping("{idNum}")
    public ResponseEntity<String> deleteClient (@PathVariable String idNum ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientService.deleteClientByIdNum(idNum));
    }

}
