package com.appli_banking.bankingAPP.models;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "contact")
public class Contact extends AbstractEntity{

    @Column(name = "nom_contact")
    private String firstname;

    @Column(name = "prenom_contact")
    private String lastname;

    @Column(name = "email_contact")
    private String email;

    private String iban;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User user;
}
