package com.appli_banking.bankingAPP.controllers;


import com.appli_banking.bankingAPP.dto.TransactionsDate;
import com.appli_banking.bankingAPP.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService service;


    @GetMapping("/sum-by-date/{IdUser}")
    public ResponseEntity<List<TransactionsDate>> findSumTransactionByDate(
            @PathVariable("IdUser") Integer userId,
            @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
            ){
        return ResponseEntity.ok(service.findSumTransactionByDate(startDate,endDate,userId));
    }

    @GetMapping("/balance-account/{userId}")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable Integer userId){
        return ResponseEntity.ok(service.getAccountBalance(userId));
    }

    @GetMapping("/highest-transfer/{userId}")
    public ResponseEntity<BigDecimal> highestTransfer(@PathVariable Integer userId){
        return ResponseEntity.ok(service.highestTransfert(userId));
    }

    @GetMapping("/highest-depose/{userId}")
    public ResponseEntity<BigDecimal> highestDeposit(@PathVariable Integer userId){
        return ResponseEntity.ok(service.highestDeposit(userId));
    }
}
