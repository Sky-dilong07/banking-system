package com.banking.banking_system.service;


import com.banking.banking_system.model.Account;
import com.banking.banking_system.model.Customer;
import com.banking.banking_system.model.Transaction;
import com.banking.banking_system.repository.AccountRepository;
import com.banking.banking_system.repository.CustomerRepository;
import com.banking.banking_system.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository,
                          CustomerRepository customerRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
}

// 1. Create Account
public Account createAccount(Long customerId, String accountType, String accountNumber) {
    Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    Account account = new Account();
    account.setAccountNumber(accountNumber);
    account.setAccountType(accountType);
    account.setBalance(BigDecimal.ZERO);
    account.setCustomer(customer);

    return accountRepository.save(account);
}

    // 2. Deposit
    public String deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) throw new RuntimeException("Account not found");

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        saveTransaction(account, "Deposit", amount);
        return "₹" + amount + " deposited successfully!";
    }

    // 3. Withdraw
    public String withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) throw new RuntimeException("Account not found");

        if (account.getBalance().compareTo(amount) < 0)
            throw new RuntimeException("Insufficient balance");

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        saveTransaction(account, "Withdraw", amount);
        return "₹" + amount + " withdrawn successfully!";
    }

    // 4. Transfer
    public String transfer(String fromAccNum, String toAccNum, BigDecimal amount) {
        Account from = accountRepository.findByAccountNumber(fromAccNum);
        Account to = accountRepository.findByAccountNumber(toAccNum);

        if (from == null || to == null)
            throw new RuntimeException("Invalid account details");

        if (from.getBalance().compareTo(amount) < 0)
            throw new RuntimeException("Insufficient funds in sender account");

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);

        saveTransaction(from, "Transfer - Sent", amount);
        saveTransaction(to, "Transfer - Received", amount);

        return "₹" + amount + " transferred from " + fromAccNum + " to " + toAccNum;
    }

    // 5. Get Account Details
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    // 6. Get Transaction History
    public List<Transaction> getTransactions(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return transactionRepository.findByAccount(account);
    }

    // 7. Helper - Save Transaction
    private void saveTransaction(Account account, String type, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
    }
}
