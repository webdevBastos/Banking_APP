package com.appli_banking.bankingAPP.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "utilisateurs")
public class User extends AbstractEntity implements UserDetails {


    @Column(name = "nom")
    private String firstname;

    @Column(name = "prenom")
    private String lastname;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "motdepasse")
    private String password;

    private boolean active;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @OneToOne
    private Account account;

    @OneToOne
    private Role role;

    @Override // la liste des autorités (les rôles de cet utilisateur)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getRole()));
        //puisque Role one to one donc singleton psq utilisateur a un seul role d'apres relation entre user et role
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } //compte n'est pas experes

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } //compte n'est pas bloqué

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
