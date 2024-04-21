package com.appli_banking.bankingAPP.services.impl;

import com.appli_banking.bankingAPP.dto.AccountDto;
import com.appli_banking.bankingAPP.exceptions.OperationNonPermittedException;
import com.appli_banking.bankingAPP.models.Account;
import com.appli_banking.bankingAPP.repositories.AccountRepository;
import com.appli_banking.bankingAPP.services.AccountService;
import com.appli_banking.bankingAPP.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ObjectsValidator<AccountDto> validator;


    @Override
    @Transactional
    public Integer save(AccountDto dto) {

        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent(); //pour dire que user a deja un compte
        if (userHasAlreadyAnAccount && account.getUser().isActive()){ // verifier si le compte est active sinon OperationNonPermittedException
            throw  new OperationNonPermittedException(
                    "The selected user has already an active account",
                    "Create account",
                    "Account Service",
                    "Create account not permitted"
            );
        }
        // generate random IBAN lors de la création d'un nouveau compte sinon ne modifie pas le IBAN
        if (dto.getId() == null) {
            account.setIban(generateRandomIban());
        }
        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No account was found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    private String generateRandomIban() {
         // todo generate an iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();
        //verifier si iban existe
         boolean ibanExists = accountRepository.findByIban(iban).isPresent();
        //si il existe alors generer new Random iban
        if (ibanExists) {
            generateRandomIban();
        }
        //sinon return generated iban

        return iban;
    }

}
