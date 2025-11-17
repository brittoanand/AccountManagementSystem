package com.accountmanagementsystem.service;

import com.accountmanagementsystem.dto.TransactionRequest;
import com.accountmanagementsystem.dto.TransactionResponse;
import com.accountmanagementsystem.exception.ResourceNotFoundException;
import com.accountmanagementsystem.model.Account;
import com.accountmanagementsystem.model.OperationType;
import com.accountmanagementsystem.model.Transaction;
import com.accountmanagementsystem.repository.AccountRepository;
import com.accountmanagementsystem.repository.OperationTypeRepository;
import com.accountmanagementsystem.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testCreateTransaction_Success_Purchase() {
        TransactionRequest request = new TransactionRequest(1L, 1L, BigDecimal.valueOf(100));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));

        when(operationTypeRepository.findById(1L)).thenReturn(Optional.of(new OperationType()));

        when(transactionRepository.getAccountBalance(1L)).thenReturn(BigDecimal.ZERO);

        Transaction savedTransaction = new Transaction();
        savedTransaction.setTransactionId(10L);
        savedTransaction.setAccountId(1L);
        savedTransaction.setOperationTypeId(1L);
        savedTransaction.setAmount(BigDecimal.valueOf(-100));
        savedTransaction.setEventDate(LocalDateTime.now());

        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        TransactionResponse response = transactionService.createTransaction(request);

        assertThat(response.transaction_id()).isEqualTo(10L);
        assertThat(response.account_id()).isEqualTo(1L);
        assertThat(response.operation_type_id()).isEqualTo(1L);
        assertThat(response.amount()).isEqualTo(BigDecimal.valueOf(-100));

        verify(transactionRepository, times(1)).getAccountBalance(1L);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testCreateTransaction_InvalidAccount() {
        TransactionRequest request = new TransactionRequest(99L, 1L, BigDecimal.valueOf(100));
        when(accountRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transactionService.createTransaction(request));
    }

    @Test
    void testCreateTransaction_InvalidOperationType() {
        TransactionRequest request = new TransactionRequest(1L, 5L, BigDecimal.valueOf(100));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
        when(operationTypeRepository.findById(5L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transactionService.createTransaction(request));
    }
}