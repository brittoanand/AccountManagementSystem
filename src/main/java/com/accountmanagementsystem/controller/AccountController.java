package com.accountmanagementsystem.controller;

import com.accountmanagementsystem.dto.AccountRequest;
import com.accountmanagementsystem.dto.AccountResponse;
import com.accountmanagementsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        log.info("Received request to create account with document number: {}", accountRequest.document_number());
        AccountResponse accountResponse = accountService.createAccount(accountRequest);
        log.info("Account created successfully");
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping("/accounts")
    public ResponseEntity<AccountResponse> getAccountById(@RequestParam(required = true) Long accountId) {
        log.info("Fetching account with ID: {}", accountId);
        AccountResponse accountResponse = accountService.getAccountById(accountId);
        log.info("Retrieved account details successfully");
        return ResponseEntity.ok(accountResponse);
    }
}