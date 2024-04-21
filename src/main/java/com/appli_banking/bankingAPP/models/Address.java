package com.appli_banking.bankingAPP.models;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "adresse")
public class Address extends AbstractEntity{

    @Column(name = "rue")
    private String street;

    @Column(name = "num_maison")
    private Integer houseNumber;

    @Column(name = "zipCode")
    private Integer zipCode;

    @Column(name = "ville")
    private String city;

    @Column(name = "pays")
    private String country;

    @OneToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;
}
