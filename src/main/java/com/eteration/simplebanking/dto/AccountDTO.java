package com.eteration.simplebanking.dto;

import java.io.Serializable;

public class AccountDTO implements Serializable {

    private String owner;
    private String accountNumber;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
