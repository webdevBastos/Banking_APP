package com.appli_banking.bankingAPP.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TransactionsDate {

    LocalDate getTransactionDate();
    BigDecimal getAmount();
}
