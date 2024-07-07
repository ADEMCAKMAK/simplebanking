package com.eteration.simplebanking.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
// This class is a placeholder you can change the complete implementation

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Account implements Serializable {

    @Id
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "BALANCE")
    private double balance;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public Account() {
    }

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.transactions = new ArrayList<>();
    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void post(Transaction transaction) {
        transactions.add(transaction);
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        this.balance += amount;
        transactions.add(new DepositTransaction(amount));
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (this.balance >= amount) {
            this.balance -= amount;
            transactions.add(new WithdrawalTransaction(amount));
        } else {
            throw new InsufficientBalanceException("Insufficient balance.");
        }
    }

}
