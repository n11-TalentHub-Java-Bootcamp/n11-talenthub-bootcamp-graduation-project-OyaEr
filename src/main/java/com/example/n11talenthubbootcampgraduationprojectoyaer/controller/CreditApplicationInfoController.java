package com.example.n11talenthubbootcampgraduationprojectoyaer.controller;

import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.dto.CreditStatusResponseDto;
import com.example.n11talenthubbootcampgraduationprojectoyaer.service.CreditApplicationInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/creditAppInfos")
@CrossOrigin
@Tag(name = "Credit Application Info Controller", description = "We are viewing the loan application result")
public class CreditApplicationInfoController {

    @Autowired
    private CreditApplicationInfoService infoService;

    @Operation(summary = "We return the customer's credit results.")
    @PostMapping("")
     public ResponseEntity<List<CreditStatusResponseDto>> getCreditApproval (@RequestBody CreditStatusDto creditStatusDto){
        return ResponseEntity.status(HttpStatus.OK).body(infoService.getCreditStatus(creditStatusDto));
    }
}
