package com.appli_banking.bankingAPP.controllers;


import com.appli_banking.bankingAPP.dto.UserDto;
import com.appli_banking.bankingAPP.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.save(userDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserDto> findById(@PathVariable("idUser") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping("/validate/{idUser}")
    public ResponseEntity<Integer> validateAccount(@PathVariable("idUser") Integer id) {
        return ResponseEntity.ok(service.validateAccount(id));
    }

    @PatchMapping("/invalidate/{idUser}")
    public ResponseEntity<Integer> invalidateAccount(@PathVariable("idUser") Integer id){
        return ResponseEntity.ok(service.invalidateAccount(id));
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> delete(@PathVariable("idUser") Integer id){
        service.delete(id);
        return ResponseEntity.accepted().build(); //c'est à dire utilisateur a été supprimer (la demande a été accepter)
    }
}
