package com.appli_banking.bankingAPP.repositories;

import com.appli_banking.bankingAPP.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositoy extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
