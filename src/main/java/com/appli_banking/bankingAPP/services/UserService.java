package com.appli_banking.bankingAPP.services;

import com.appli_banking.bankingAPP.dto.AuthenticationRequest;
import com.appli_banking.bankingAPP.dto.AuthenticationResponse;
import com.appli_banking.bankingAPP.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;


//userDto psq on doit pas exposer les entity au monde exterieur
public interface UserService extends AbstractService<UserDto> {
    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto userDto);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
