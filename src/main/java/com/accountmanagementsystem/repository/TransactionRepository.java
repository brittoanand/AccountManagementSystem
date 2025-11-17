package com.accountmanagementsystem.repository;

import com.accountmanagementsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    @Query(value = "select coalesce(sum(amount), 0) from transactions where account_id = ?", nativeQuery = true)
    BigDecimal getAccountBalance(Long accountId);
}