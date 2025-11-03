package com.accountmanagementsystem.service;

import com.accountmanagementsystem.dto.TransactionRequest;
import com.accountmanagementsystem.dto.TransactionResponse;
import com.accountmanagementsystem.exception.ResourceNotFoundException;
import com.accountmanagementsystem.model.Account;
import com.accountmanagementsystem.model.Transaction;
import com.accountmanagementsystem.repository.AccountRepository;
import com.accountmanagementsystem.repository.OperationTypeRepository;
import com.accountmanagementsystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;

    private final OperationTypeRepository operationTypeRepository;

    private final TransactionRepository transactionRepository;

    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        accountRepository.findById(transactionRequest.account_id())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid account"));

        operationTypeRepository.findById(transactionRequest.operation_type_id())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid operation type"));

        BigDecimal amount = transactionRequest.operation_type_id() <= 3 ? transactionRequest.amount().negate()
                : transactionRequest.amount();

        Transaction transaction = new Transaction();
        transaction.setAccountId(transactionRequest.account_id());
        transaction.setOperationTypeId(transactionRequest.operation_type_id());
        transaction.setAmount(amount);
        transaction.setEventDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);

        return new TransactionResponse(
                savedTransaction.getTransactionId(),
                savedTransaction.getAccountId(),
                savedTransaction.getOperationTypeId(),
                savedTransaction.getAmount()
        );
    }
}