package com.devhas.demo_spring_batch.repository;

import com.devhas.demo_spring_batch.entity.BankTransactionEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransactionEntry, Long> {
}
