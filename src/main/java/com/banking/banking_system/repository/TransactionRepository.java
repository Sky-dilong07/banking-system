package com.banking.banking_system.repository;

import com.banking.banking_system.model.Account;
import com.banking.banking_system.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount(Account account);
}
