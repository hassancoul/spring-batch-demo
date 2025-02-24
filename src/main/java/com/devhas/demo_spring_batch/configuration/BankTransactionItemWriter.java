package com.devhas.demo_spring_batch.configuration;

import com.devhas.demo_spring_batch.entity.BankTransactionEntry;
import com.devhas.demo_spring_batch.repository.BankTransactionRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class BankTransactionItemWriter implements ItemWriter <BankTransactionEntry>{

    private final BankTransactionRepository bankTransactionRepository;

    public BankTransactionItemWriter(BankTransactionRepository bankTransactionRepository) {
        this.bankTransactionRepository = bankTransactionRepository;
    }

    @Override
    public void write(@NonNull Chunk<? extends BankTransactionEntry> chunk) {
        bankTransactionRepository.saveAll(chunk);
    }
}
