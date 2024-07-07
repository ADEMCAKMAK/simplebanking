package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

// This class is a placeholder you can change the complete implementation
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EntityListeners(AuditingEntityListener.class)
public abstract class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @CreatedDate
    @Column(name = "DATE", updatable = false)
    private LocalDateTime date;

    @Column(name = "AMOUNT")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_NUMBER_ID", referencedColumnName = "ACCOUNT_NUMBER")
    private Account account;

    @Column(name="DTYPE",insertable=false, updatable=false)
    private String type;

    public String getType() {
        return type;
    }

    @JsonProperty("approvalCode")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction() {
    }

    protected Transaction(double amount) {
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public abstract void post(Account account);

    public abstract String getTransactionType();
}
