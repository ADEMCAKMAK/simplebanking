package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class CheckTransaction extends  Transaction {

    public CheckTransaction() {
        super();
    }

    public CheckTransaction(double amount) {
        super(amount);
    }

    @Override
    public void post(Account account) {
        account.updateBalance(-this.getAmount());
        setAccount(account);
    }

    @Override
    public String getTransactionType() {
        return "check";
    }
}
