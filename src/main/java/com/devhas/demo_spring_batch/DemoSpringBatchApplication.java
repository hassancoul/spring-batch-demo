package com.devhas.demo_spring_batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class DemoSpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBatchApplication.class, args);
	}

}
