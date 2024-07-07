package com.eteration.simplebanking.services;

import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.handler.TransactionHandler;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// This class is a placeholder you can change the complete implementation
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final List<TransactionHandler<? extends Transaction>> transactionHandlers;

    public AccountService(AccountRepository accountRepository,
                          List<TransactionHandler<? extends Transaction>> transactionHandlers) {
        this.accountRepository = accountRepository;
        this.transactionHandlers = transactionHandlers;
    }

    @Transactional
    public Account createAccount(AccountDTO accountDTO){
        Account account = new Account(accountDTO.getOwner(), accountDTO.getAccountNumber());
        return accountRepository.save(account);
    }

    public Account findAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).get();
    }

    @Transactional
    public void post(Account account, TransactionDTO transactionDTO, String type) {
        Optional<TransactionHandler<? extends Transaction>> optionalTransactionHandler = transactionHandlers
                .stream().filter(th -> th.supports(type)).findFirst();
        optionalTransactionHandler.ifPresentOrElse(transactionHandler ->
                transactionHandler.handle(account, transactionDTO), () ->
        {
            throw new UnsupportedOperationException("No handler found for transaction type: " + type);
        });
    }
}
