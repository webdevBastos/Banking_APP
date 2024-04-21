package com.appli_banking.bankingAPP.controllers;


import com.appli_banking.bankingAPP.dto.AccountDto;
import com.appli_banking.bankingAPP.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(service.save(accountDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> findById(@PathVariable("accountId") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> delete(@PathVariable Integer accountId) {
        service.delete(accountId);
        return ResponseEntity.accepted().build();
    }
}
