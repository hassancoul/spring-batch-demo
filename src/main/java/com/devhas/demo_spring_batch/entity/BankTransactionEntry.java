package com.devhas.demo_spring_batch.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "bank_transactions")
@ToString
public class BankTransactionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private LocalDateTime transactionDate;
    private BigDecimal montant;
    private String type;
    private String compteSource;
    private String compteDestination;


    @Builder
    public BankTransactionEntry(
            String transactionId,
            LocalDateTime transactionDate,
            BigDecimal montant,
            String type,
            String compteSource,
            String compteDestination
    ) {

        assert transactionId != null : "TransactionId cannot be null";
        assert transactionDate != null : "TransactionDate cannot be null";
        assert montant != null : "Montant cannot be null";
        assert type != null : "Transaction type cannot be null";
        assert compteSource != null : "CompteSource cannot be null";
        assert compteDestination != null : "CompteDestination cannot be null";

        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.montant = montant;
        this.type = type;
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }

}
