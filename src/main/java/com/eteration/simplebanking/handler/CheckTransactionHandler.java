package com.eteration.simplebanking.handler;

import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.CheckTransaction;
import com.eteration.simplebanking.repository.CheckTransactionRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CheckTransactionHandler implements TransactionHandler<CheckTransaction> {

    private final CheckTransactionRepository checkTransactionRepository;

    public CheckTransactionHandler(CheckTransactionRepository checkTransactionRepository) {
        this.checkTransactionRepository = checkTransactionRepository;
    }

    @Override
    public void handle(Account account, TransactionDTO transactionDTO) {
        CheckTransaction checkTransaction = new CheckTransaction(transactionDTO.getAmount());
        checkTransaction.post(account);
        checkTransactionRepository.save(checkTransaction);
    }

    @Override
    public boolean supports(String type) {
        return Objects.equals(type, "check");
    }

}
