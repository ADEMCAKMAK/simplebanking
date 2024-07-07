package com.eteration.simplebanking.handler;

import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.repository.DepositTransactionRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class DepositTransactionHandler implements TransactionHandler<DepositTransaction> {

    private final DepositTransactionRepository depositTransactionRepository;

    public DepositTransactionHandler(DepositTransactionRepository depositTransactionRepository) {
        this.depositTransactionRepository = depositTransactionRepository;
    }

    @Override
    public void handle(Account account, TransactionDTO transactionDTO) {
        DepositTransaction depositTransaction = new DepositTransaction(transactionDTO.getAmount());
        depositTransaction.post(account);
        depositTransactionRepository.save(depositTransaction);
    }

    @Override
    public boolean supports(String type) {
        return Objects.equals(type, "deposit");
    }
}
