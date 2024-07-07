package com.eteration.simplebanking.handler;

import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repository.WithdrawalTransactionRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class WithdrawalTransactionHandler implements TransactionHandler<WithdrawalTransaction> {

    private final WithdrawalTransactionRepository withdrawalTransactionRepository;

    public WithdrawalTransactionHandler(WithdrawalTransactionRepository withdrawalTransactionRepository) {
        this.withdrawalTransactionRepository = withdrawalTransactionRepository;
    }

    @Override
    public void handle(Account account, TransactionDTO transactionDTO) {
        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(transactionDTO.getAmount());
        withdrawalTransaction.post(account);
        withdrawalTransactionRepository.save(withdrawalTransaction);
    }

    @Override
    public boolean supports(String type) {
        return Objects.equals(type, "withdrawal");
    }

}
