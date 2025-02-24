package com.devhas.demo_spring_batch.web.rest;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "job")
public class BankTransactionController {

    private final JobLauncher bankTransactionJobLauncher;
    private final Job bankTransactionJob;

    public BankTransactionController(JobLauncher bankTransactionJobLauncher, Job bankTransactionJob) {
        this.bankTransactionJobLauncher = bankTransactionJobLauncher;
        this.bankTransactionJob = bankTransactionJob;
    }


    @GetMapping("/start")
    public ResponseEntity<String> startJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("id", System.currentTimeMillis())
                .toJobParameters();

        JobExecution execution = bankTransactionJobLauncher.run(bankTransactionJob, jobParameters);

        return ResponseEntity.ok("Job Status: " + execution.getStatus());
    }
}
