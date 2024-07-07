package com.eteration.simplebanking.repository;


import com.eteration.simplebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository<T extends Transaction, I> extends JpaRepository<T, I> {
}
