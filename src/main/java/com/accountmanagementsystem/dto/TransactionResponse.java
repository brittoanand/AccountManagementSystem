package com.accountmanagementsystem.dto;

import java.math.BigDecimal;

public record TransactionResponse(Long transaction_id, Long account_id, Long operation_type_id, BigDecimal amount) {
}