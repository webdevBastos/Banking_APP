package com.appli_banking.bankingAPP.controllers;


import com.appli_banking.bankingAPP.dto.AddressDto;
import com.appli_banking.bankingAPP.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(service.save(addressDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{address_id}")
    public ResponseEntity<AddressDto> findById(@PathVariable("address_id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{address_id}")
    public ResponseEntity<Void> delete(@PathVariable("address_id") Integer id) {
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
}
