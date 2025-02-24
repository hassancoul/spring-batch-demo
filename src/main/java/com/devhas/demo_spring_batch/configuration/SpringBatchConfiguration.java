package com.devhas.demo_spring_batch.configuration;

import com.devhas.demo_spring_batch.dto.BankTransactionInput;
import com.devhas.demo_spring_batch.entity.BankTransactionEntry;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.RecordFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SpringBatchConfiguration {

    @Bean
    LineMapper<BankTransactionInput> lineMapper() {
        DefaultLineMapper<BankTransactionInput> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "date", "montant", "type", "compte_source", "compte_destination");
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new RecordFieldSetMapper<>(BankTransactionInput.class));

        return lineMapper;
    }

    @Bean
    public FlatFileItemReader<BankTransactionInput> reader(@Value("${bank.input.transactions.file}") Resource resource) {
        return new FlatFileItemReaderBuilder<BankTransactionInput>()
                .name("bankTransactionReader")
                .linesToSkip(1)
                .resource(resource)
                .lineMapper(lineMapper())
                .build();
    }

    @Bean
    public Job importTransactionJob(JobRepository jobRepository, Step step1, JobExecutionListener jobExecutionListener) {
        return new JobBuilder("importTransactionJob", jobRepository)
                .listener(jobExecutionListener)
                .start(step1)
                .build();
    }

    @Bean
    BankTransactionItemProcessor bankTransactionItemProcessor() {
        return new BankTransactionItemProcessor();
    }

    @Bean
    public Step step1(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            FlatFileItemReader<BankTransactionInput> reader,
            BankTransactionItemProcessor processor,
            BankTransactionItemWriter writer
    ) {
        return new StepBuilder("importTransactionStep1", jobRepository)
                .<BankTransactionInput, BankTransactionEntry>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public JobLauncher bankTransactionJobLauncher(JobRepository jobRepository) throws Exception {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
}
