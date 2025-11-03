package com.accountmanagementsystem.dto;

import java.math.BigDecimal;

public record TransactionRequest(Long account_id, Long operation_type_id, BigDecimal amount) {
}