package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.CheckTransaction;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckTransactionRepository extends TransactionRepository<CheckTransaction,Long> {

}
