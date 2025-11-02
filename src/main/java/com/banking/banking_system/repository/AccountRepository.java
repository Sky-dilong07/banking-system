package com.banking.banking_system.repository;

import com.banking.banking_system.model.Account;
import com.banking.banking_system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomer(Customer customer);
    Account findByAccountNumber(String accountNumber);
}
