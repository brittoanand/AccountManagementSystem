package com.accountmanagementsystem.controller;

import com.accountmanagementsystem.dto.AccountRequest;
import com.accountmanagementsystem.dto.AccountResponse;
import com.accountmanagementsystem.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    void testCreateAccount_Success() {
        AccountRequest request = new AccountRequest("12345");
        AccountResponse response = new AccountResponse(1L, "12345");

        when(accountService.createAccount(request)).thenReturn(response);

        ResponseEntity<AccountResponse> result = accountController.createAccount(request);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(response);
        verify(accountService, times(1)).createAccount(request);
    }

    @Test
    void testGetAccountById_Success() {
        Long accountId = 1L;
        AccountResponse response = new AccountResponse(accountId, "12345");

        when(accountService.getAccountById(accountId)).thenReturn(response);

        ResponseEntity<AccountResponse> result = accountController.getAccountById(accountId);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(response);
        verify(accountService, times(1)).getAccountById(accountId);
    }
}