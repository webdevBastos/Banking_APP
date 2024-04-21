package com.appli_banking.bankingAPP.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "transaction")
public class Transaction extends AbstractEntity{


    private BigDecimal amount;

    @Column(name = "trans_type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String destinationIBAN;
    @Column(updatable = false)
    private LocalDate transactinDate;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;
}
