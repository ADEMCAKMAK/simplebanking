package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {

    @Spy
    @InjectMocks
    private AccountController controller;
 
    @Mock
    private AccountService service;
    
    @Test
    void givenId_Credit_thenReturnJson() {
        Account account = new Account("Kerem Karaca", "17892");
        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<TransactionStatus> result = controller.credit( "17892", new TransactionDTO(1000.0));
        verify(service, times(1)).findAccount("17892");
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void givenId_CreditAndThenDebit_thenReturnJson() {
        Account account = new Account("Kerem Karaca", "17892");
        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<TransactionStatus> result = controller.credit( "17892", new TransactionDTO(1000.0));
        ResponseEntity<TransactionStatus> result2 = controller.debit( "17892", new TransactionDTO(50.0));
        verify(service, times(2)).findAccount("17892");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(950.0, account.getBalance(),0.001);
    }

    @Test
    void givenId_CreditAndThenDebitMoreGetException_thenReturnJson() {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            Account account = new Account("Kerem Karaca", "17892");

            doReturn(account).when(service).findAccount( "17892");
            ResponseEntity<TransactionStatus> result = controller.credit( "17892", new TransactionDTO(1000.0));
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(1000.0, account.getBalance(),0.001);
            verify(service, times(1)).findAccount("17892");

            ResponseEntity<TransactionStatus> result2 = controller.debit( "17892", new TransactionDTO(5000.0));
        });
    }

    @Test
    void givenId_GetAccount_thenReturnJson() {
        Account account = new Account("Kerem Karaca", "17892");
        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<Account> result = controller.getAccount("17892");
        verify(service, times(1)).findAccount("17892");
        assertEquals(account, result.getBody());
    }

}
