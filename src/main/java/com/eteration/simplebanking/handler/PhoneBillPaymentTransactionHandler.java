package com.eteration.simplebanking.handler;

import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.repository.PhoneBillPaymentTransactionRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PhoneBillPaymentTransactionHandler implements TransactionHandler<PhoneBillPaymentTransaction> {

    private final PhoneBillPaymentTransactionRepository phoneBillPaymentTransactionRepository;

    public PhoneBillPaymentTransactionHandler(PhoneBillPaymentTransactionRepository phoneBillPaymentTransactionRepository) {
        this.phoneBillPaymentTransactionRepository = phoneBillPaymentTransactionRepository;
    }

    @Override
    public void handle(Account account, TransactionDTO transactionDTO) {
        PhoneBillPaymentTransaction phoneBillPaymentTransaction = new PhoneBillPaymentTransaction(transactionDTO.getAmount());
        phoneBillPaymentTransaction.post(account);
        phoneBillPaymentTransactionRepository.save(phoneBillPaymentTransaction);
    }

    @Override
    public boolean supports(String type) {
        return Objects.equals(type, "phoneBillPayment");
    }
}
