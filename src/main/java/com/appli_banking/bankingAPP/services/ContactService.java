package com.appli_banking.bankingAPP.services;

import com.appli_banking.bankingAPP.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{

    List<ContactDto> findAllByUserId(Integer userId);
}
