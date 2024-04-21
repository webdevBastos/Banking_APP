package com.appli_banking.bankingAPP.controllers;

import com.appli_banking.bankingAPP.dto.ContactDto;
import com.appli_banking.bankingAPP.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody ContactDto contactDto){
        return ResponseEntity.ok(service.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{contact_id}")
    public ResponseEntity<ContactDto> findById(@PathVariable("contact_id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ContactDto>> findAllByUserId(@PathVariable("userId") Integer user_id) {
        return ResponseEntity.ok(service.findAllByUserId(user_id));
    }

    @DeleteMapping("/{contact_id}")
    public ResponseEntity<Void> delete(@PathVariable Integer contact_id) {
        service.delete(contact_id);
        return ResponseEntity.accepted().build();
    }


}
