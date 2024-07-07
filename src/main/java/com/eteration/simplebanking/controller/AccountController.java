package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


// This class is a placeholder you can change the complete implementation
@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
        Account account = accountService.createAccount(accountDTO);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable final String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable final String accountNumber,
                                                    @RequestBody TransactionDTO transactionDTO) {
        Account account = accountService.findAccount(accountNumber);
        accountService.post(account, transactionDTO, "deposit");
        return ResponseEntity.ok(new TransactionStatus("1"));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable final String accountNumber,
                                                   @RequestBody TransactionDTO transactionDTO) {
        Account account = accountService.findAccount(accountNumber);
        accountService.post(account, transactionDTO, "withdrawal");
        return ResponseEntity.ok(new TransactionStatus("1"));
	}

    @PostMapping("/phoneBillPayment/{accountNumber}")
    public ResponseEntity<TransactionStatus> phoneBillPayment(@PathVariable final String accountNumber,
                                                              @RequestBody TransactionDTO transactionDTO) {
        Account account = accountService.findAccount(accountNumber);
        accountService.post(account, transactionDTO, "phoneBillPayment");
        return ResponseEntity.ok(new TransactionStatus("1"));
    }

    @PostMapping("/check/{accountNumber}")
    public ResponseEntity<TransactionStatus> check(@PathVariable final String accountNumber,
                                                   @RequestBody TransactionDTO transactionDTO) {
        Account account = accountService.findAccount(accountNumber);
        accountService.post(account, transactionDTO, "check");
        return ResponseEntity.ok(new TransactionStatus("1"));
    }
}