package com.eteration.simplebanking.handler;

import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;

public interface TransactionHandler<T extends Transaction> {
    void handle(Account account, TransactionDTO transactionDTO);
    boolean supports(String type);
}
