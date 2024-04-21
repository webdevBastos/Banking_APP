package com.appli_banking.bankingAPP.services.impl;

import com.appli_banking.bankingAPP.config.JwtUtils;
import com.appli_banking.bankingAPP.dto.AccountDto;
import com.appli_banking.bankingAPP.dto.AuthenticationRequest;
import com.appli_banking.bankingAPP.dto.AuthenticationResponse;
import com.appli_banking.bankingAPP.dto.UserDto;
import com.appli_banking.bankingAPP.models.Role;
import com.appli_banking.bankingAPP.models.User;
import com.appli_banking.bankingAPP.repositories.RoleRepositoy;
import com.appli_banking.bankingAPP.repositories.UserRepository;
import com.appli_banking.bankingAPP.services.AccountService;
import com.appli_banking.bankingAPP.services.UserService;
import com.appli_banking.bankingAPP.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;
    private final ObjectsValidator<UserDto> validator;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;
    private  final RoleRepositoy roleRepositoy;
    @Override
    public Integer save(UserDto dto) {
        //avant de persister les données, on doit les validé d'abord
        validator.validate(dto);
        //apres la validation de notre objet, on va persister les donnees | et avant de persister on doit transformer le userDto vers un objet User
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()//sa permet d'envoyer un stream utilisateur
                .map(UserDto::fromEntity)//on va mapper(c'est a dire pour chaque element) on va appeler la methode fromEntity(methode de transformation)
                .collect(Collectors.toList()); //rassembler les informations
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the privided ID" +id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the ID : " + id));
        user.setActive(true);// si il est actif on le crée un compte bancaire
        userRepository.save(user);
        AccountDto accountDto = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(accountDto);

        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the ID : " + id));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public AuthenticationResponse register(UserDto userDto) {
        validator.validate(userDto);
        User user = UserDto.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(findOrCreateRole(ROLE_USER));
        var savedUser = userRepository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullName", savedUser.getFirstname() + " " + savedUser.getLastname());
        String token = jwtUtils.generateToken(savedUser,claims);
        return AuthenticationResponse.builder().token(token).build();
    }

    @Override
    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final User user = userRepository.findByEmail(request.getEmail()).get();

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullName", user.getFirstname() + " " + user.getLastname());
        final String token = jwtUtils.generateToken(user, claims);
        return AuthenticationResponse.builder().token(token).build();
    }

    private Role findOrCreateRole(String roleNAme) {
        Role role = roleRepositoy.findByRole(roleNAme);
        if (role == null) {
            return roleRepositoy.save(
                    Role.builder()
                            .role(roleNAme)
                            .build()
            );
        }
        return role;
    }
}
