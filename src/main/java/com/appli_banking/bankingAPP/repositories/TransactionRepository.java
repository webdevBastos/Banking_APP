package com.appli_banking.bankingAPP.repositories;

import com.appli_banking.bankingAPP.dto.TransactionsDate;
import com.appli_banking.bankingAPP.models.Transaction;
import com.appli_banking.bankingAPP.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUserId(Integer userId);


    @Query("select  sum(t.amount) from  Transaction t where t.user.id = :userId")
    BigDecimal findByAccountBalance(Integer userId);

    @Query("select max(abs(t.amount)) as amount from Transaction t where t.user.id = :userId and t.type = :transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select t.transactinDate as transactionDate, sum(t.amount) as amount from Transaction t where t.user.id = :userId and t.creationDate between :start and :end group by t.transactinDate")
    List<TransactionsDate> findSumTransactionByDate(LocalDateTime start, LocalDateTime end, Integer userId);

}
