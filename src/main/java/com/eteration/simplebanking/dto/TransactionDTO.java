package com.eteration.simplebanking.dto;

import java.io.Serializable;

public class TransactionDTO implements Serializable {
    private double amount;

    public TransactionDTO() {
    }

    public TransactionDTO(double amount) {
        setAmount(amount);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
