package com.appli_banking.bankingAPP.services.impl;

import com.appli_banking.bankingAPP.dto.TransactionsDate;
import com.appli_banking.bankingAPP.models.TransactionType;
import com.appli_banking.bankingAPP.repositories.TransactionRepository;
import com.appli_banking.bankingAPP.services.StatisticsService;
import com.appli_banking.bankingAPP.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final TransactionRepository repository;

    @Override
    public List<TransactionsDate>   findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Integer userId) {
        LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23,59,59));
        return repository.findSumTransactionByDate(start,end,userId);
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return repository.findByAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfert(Integer userId) {
        return repository.findHighestAmountByTransactionType(userId, TransactionType.TRANSFERT);
    }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return repository.findHighestAmountByTransactionType(userId, TransactionType.DEPOSIT);
    }
}
