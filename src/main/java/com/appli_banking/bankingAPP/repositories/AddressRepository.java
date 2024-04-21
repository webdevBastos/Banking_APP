package com.appli_banking.bankingAPP.repositories;

import com.appli_banking.bankingAPP.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
