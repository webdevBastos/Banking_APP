package com.appli_banking.bankingAPP.controllers;


import com.appli_banking.bankingAPP.dto.AuthenticationRequest;
import com.appli_banking.bankingAPP.dto.AuthenticationResponse;
import com.appli_banking.bankingAPP.dto.UserDto;
import com.appli_banking.bankingAPP.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto user) {

        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        /* authenticationManager c'est le gestionnaire d'authentification, vérifie si l'authentication est valide ou pas */

        return ResponseEntity.ok(userService.authenticate(request));
    }

}
