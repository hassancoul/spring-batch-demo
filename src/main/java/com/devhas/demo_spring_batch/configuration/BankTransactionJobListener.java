package com.devhas.demo_spring_batch.configuration;

import com.devhas.demo_spring_batch.repository.BankTransactionRepository;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class BankTransactionJobListener implements JobExecutionListener {

    private final BankTransactionRepository bankTransactionRepository;

    public BankTransactionJobListener(BankTransactionRepository bankTransactionRepository) {
        this.bankTransactionRepository = bankTransactionRepository;
    }

    @Override
    public void afterJob(@NonNull JobExecution jobExecution) {
        JobExecutionListener.super.afterJob(jobExecution);

        log.info("!!! JOB FINISHED! Time to verify the results");

        bankTransactionRepository.findAll().forEach(bankTransaction -> log.info("Found Bank transaction {} ", bankTransaction));
    }
}
