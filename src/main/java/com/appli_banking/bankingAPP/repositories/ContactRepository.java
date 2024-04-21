package com.appli_banking.bankingAPP.repositories;

import com.appli_banking.bankingAPP.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findAllByUserId(Integer userId);
}
