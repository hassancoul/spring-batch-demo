package com.devhas.demo_spring_batch.mapper;

import com.devhas.demo_spring_batch.dto.BankTransactionInput;
import com.devhas.demo_spring_batch.entity.BankTransactionEntry;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class BankTransactionMapper {
    public BankTransactionEntry toEntry(BankTransactionInput input) {

        log.info("Mapping {} to bank transaction entry", input);

        try {
            return BankTransactionEntry.builder()
                    .transactionId(input.id())
                    .transactionDate(LocalDateTime.parse(input.date(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .montant(BigDecimal.valueOf(input.montant()))
                    .type(input.type())
                    .compteSource(input.compte_source())
                    .compteDestination(input.compte_destination())
                    .build();
        } catch (Exception ex) {
            log.error("Error while mapping transaction entry", ex);
            throw ex;
        }
    }
}
