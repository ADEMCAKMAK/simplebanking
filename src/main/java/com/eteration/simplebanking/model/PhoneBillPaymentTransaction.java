package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class PhoneBillPaymentTransaction extends Transaction {

    public PhoneBillPaymentTransaction() {
    }

    public PhoneBillPaymentTransaction(double amount) {
        super(amount);
    }

    @Override
    public void post(Account account) {
        account.updateBalance(-this.getAmount());
        setAccount(account);
    }

    @Override
    public String getTransactionType() {
        return "phoneBillPayment";
    }

}
