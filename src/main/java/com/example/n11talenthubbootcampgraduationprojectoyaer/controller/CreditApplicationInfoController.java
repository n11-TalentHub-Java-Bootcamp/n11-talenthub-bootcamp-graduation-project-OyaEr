package com.example.n11talenthubbootcampgraduationprojectoyaer.controller;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.service.CreditApplicationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/creditAppInfos")
@CrossOrigin
public class CreditApplicationInfoController {

    @Autowired
    CreditApplicationInfoService infoService;

    @PostMapping("")
     public ResponseEntity<String> getCreditApproval (@RequestBody CreditStatusDto creditStatusDto){
        return ResponseEntity.status(HttpStatus.OK).body(infoService.getCreditStatus(creditStatusDto));
    }
}
