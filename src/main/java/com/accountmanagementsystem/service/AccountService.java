package com.accountmanagementsystem.service;

import com.accountmanagementsystem.dto.AccountRequest;
import com.accountmanagementsystem.dto.AccountResponse;
import com.accountmanagementsystem.exception.BadRequestException;
import com.accountmanagementsystem.exception.ResourceNotFoundException;
import com.accountmanagementsystem.model.Account;
import com.accountmanagementsystem.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountResponse createAccount(AccountRequest accountRequest) {
        if(accountRepository.existsByDocumentNumber(accountRequest.document_number())) {
            throw new BadRequestException("The account number/document number already present");
        }

        Account account = new Account();
        account.setDocumentNumber(accountRequest.document_number());

        Account savedAccount = accountRepository.save(account);

        return new AccountResponse(savedAccount.getAccountId(), savedAccount.getDocumentNumber());
    }

    public AccountResponse getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        return new AccountResponse(account.getAccountId(), account.getDocumentNumber());
    }
}