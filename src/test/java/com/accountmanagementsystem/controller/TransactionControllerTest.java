package com.accountmanagementsystem.controller;

import com.accountmanagementsystem.dto.TransactionRequest;
import com.accountmanagementsystem.dto.TransactionResponse;
import com.accountmanagementsystem.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void testCreateTransaction_Success() {
        TransactionRequest request = new TransactionRequest(1L, 2L, BigDecimal.valueOf(100));
        TransactionResponse response = new TransactionResponse(1L, 1L, 2L, BigDecimal.valueOf(-100));

        when(transactionService.createTransaction(request)).thenReturn(response);

        ResponseEntity<TransactionResponse> result = transactionController.createTransaction(request);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(response);
        verify(transactionService, times(1)).createTransaction(request);
    }
}