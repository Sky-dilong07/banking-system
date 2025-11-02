package com.banking.banking_system.controller;

import com.banking.banking_system.model.Account;
import com.banking.banking_system.model.Transaction;
import com.banking.banking_system.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // 1. Create a new account for an existing customer
    @PostMapping("/create")
    public Account createAccount(@RequestBody Map<String, String> request) {
        Long customerId = Long.parseLong(request.get("customerId"));
        String accountType = request.get("accountType");
        String accountNumber = request.get("accountNumber");

        return accountService.createAccount(customerId, accountType, accountNumber);
    }

    // 2. Deposit money
    @PostMapping("/deposit")
    public String deposit(@RequestBody Map<String, String> request) {
        String accountNumber = request.get("accountNumber");
        BigDecimal amount = new BigDecimal(request.get("amount"));
        return accountService.deposit(accountNumber, amount);
    }

    // 3. Withdraw money
    @PostMapping("/withdraw")
    public String withdraw(@RequestBody Map<String, String> request) {
        String accountNumber = request.get("accountNumber");
        BigDecimal amount = new BigDecimal(request.get("amount"));
        return accountService.withdraw(accountNumber, amount);
    }

    // 4. Transfer between accounts
    @PostMapping("/transfer")
    public String transfer(@RequestBody Map<String, String> request) {
        String fromAccount = request.get("fromAccount");
        String toAccount = request.get("toAccount");
        BigDecimal amount = new BigDecimal(request.get("amount"));
        return accountService.transfer(fromAccount, toAccount, amount);
    }

    // 5. Get account details
    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        return accountService.getAccountByNumber(accountNumber);
    }

    // 6. Get transaction history
    @GetMapping("/{accountNumber}/transactions")
    public List<Transaction> getTransactions(@PathVariable String accountNumber) {
        return accountService.getTransactions(accountNumber);
    }

}
