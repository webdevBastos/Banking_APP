package com.appli_banking.bankingAPP.controllers;



import com.appli_banking.bankingAPP.dto.TransactionDto;
import com.appli_banking.bankingAPP.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(service.save(transactionDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{transaction_id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("transaction_id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<TransactionDto>> findAllByUserId(@PathVariable("userId") Integer user_id) {
        return ResponseEntity.ok(service.findAllByUserId(user_id));
    }

    @DeleteMapping("/{transaction_id}")
    public ResponseEntity<Void> delete(@PathVariable Integer transaction_id) {
        service.delete(transaction_id);
        return ResponseEntity.accepted().build();
    }

}
