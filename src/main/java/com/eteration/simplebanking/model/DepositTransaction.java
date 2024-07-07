package com.eteration.simplebanking.model;


import javax.persistence.Entity;

// This class is a placeholder you can change the complete implementation
@Entity
public class DepositTransaction extends Transaction {

    public DepositTransaction() {
        super();
    }

    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public void post(Account account) {
        account.updateBalance(this.getAmount());
        setAccount(account);
    }

    @Override
    public String getTransactionType() {
        return "deposit";
    }

}
