package com.accountmanagementsystem.service;

import com.accountmanagementsystem.dto.AccountRequest;
import com.accountmanagementsystem.dto.AccountResponse;
import com.accountmanagementsystem.exception.BadRequestException;
import com.accountmanagementsystem.exception.ResourceNotFoundException;
import com.accountmanagementsystem.model.Account;
import com.accountmanagementsystem.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void testCreateAccount_Success() {
        AccountRequest request = new AccountRequest("12345");
        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345");

        when(accountRepository.existsByDocumentNumber("12345")).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountResponse response = accountService.createAccount(request);

        assertThat(response.account_id()).isEqualTo(1L);
        assertThat(response.document_number()).isEqualTo("12345");
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void testCreateAccount_AlreadyExists() {
        when(accountRepository.existsByDocumentNumber("12345")).thenReturn(true);
        AccountRequest request = new AccountRequest("12345");

        assertThrows(BadRequestException.class, () -> accountService.createAccount(request));
        verify(accountRepository, never()).save(any());
    }

    @Test
    void testGetAccountById_Success() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        AccountResponse response = accountService.getAccountById(1L);

        assertThat(response.account_id()).isEqualTo(1L);
        assertThat(response.document_number()).isEqualTo("12345");
    }

    @Test
    void testGetAccountById_NotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> accountService.getAccountById(1L));
    }
}