package com.appli_banking.bankingAPP.services.impl;

import com.appli_banking.bankingAPP.dto.TransactionDto;
import com.appli_banking.bankingAPP.models.Transaction;
import com.appli_banking.bankingAPP.models.TransactionType;
import com.appli_banking.bankingAPP.repositories.TransactionRepository;
import com.appli_banking.bankingAPP.services.TransactionService;
import com.appli_banking.bankingAPP.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ObjectsValidator<TransactionDto> validator;
    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        BigDecimal amount = transaction.getAmount().multiply(
                BigDecimal.valueOf(
                        transactionType(transaction.getType())
                )
        );
        transaction.setAmount(amount);
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return transactionRepository.findById(id)
                .map(TransactionDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No transaction was found with the the ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        transactionRepository.deleteById(id);
    }

    private int transactionType(TransactionType type) {
        return TransactionType.TRANSFERT == type ? -1 : 1 ; //si le type de transaction est Transfert donc c'est moins sinon c'est positif
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return transactionRepository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }
}
