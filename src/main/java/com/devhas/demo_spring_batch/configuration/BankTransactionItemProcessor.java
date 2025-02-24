package com.devhas.demo_spring_batch.configuration;

import com.devhas.demo_spring_batch.dto.BankTransactionInput;
import com.devhas.demo_spring_batch.entity.BankTransactionEntry;
import com.devhas.demo_spring_batch.mapper.BankTransactionMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

@Log4j2
public class BankTransactionItemProcessor implements ItemProcessor<BankTransactionInput, BankTransactionEntry> {

    private final BankTransactionMapper bankTransactionMapper;

    public BankTransactionItemProcessor() {
        this.bankTransactionMapper = new BankTransactionMapper();
    }

    @Override
    public BankTransactionEntry process(@NonNull final BankTransactionInput transactionInput) throws Exception {
        final BankTransactionEntry bankTransactionEntry = bankTransactionMapper.toEntry(transactionInput);

        log.info(
                "Converting BankTransactionInput ({}) into BankTransactionEntry: ({})",
                transactionInput,
                bankTransactionEntry
        );

        return bankTransactionEntry;
    }
}
