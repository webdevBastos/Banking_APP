package com.appli_banking.bankingAPP.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compte")
public class Account extends AbstractEntity{

    private String iban;


    @OneToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;
}
