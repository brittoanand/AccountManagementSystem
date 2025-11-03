package com.accountmanagementsystem.controller;

import com.accountmanagementsystem.dto.TransactionRequest;
import com.accountmanagementsystem.dto.TransactionResponse;
import com.accountmanagementsystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        log.info("Received request to create transaction with account number : {}", transactionRequest.account_id());
        TransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
        log.info("Transaction created successfully");
        return ResponseEntity.ok(transactionResponse);
    }
}