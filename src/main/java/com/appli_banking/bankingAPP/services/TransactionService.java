package com.appli_banking.bankingAPP.services;

import com.appli_banking.bankingAPP.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto>{

    List<TransactionDto> findAllByUserId(Integer userId);
}
