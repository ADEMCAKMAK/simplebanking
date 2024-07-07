package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.model.Transaction;

import java.io.Serializable;
import java.util.List;

public class AccountResponseDTO implements Serializable {

    private String accountNumber;

    private String owner;

    private double balance;

    private List<Transaction> transactions;
}
