package com.devhas.demo_spring_batch.dto;



public record BankTransactionInput (
        String id,
        String date,
        Double montant,
        String type,
        String compte_source,
        String compte_destination
) {
}
