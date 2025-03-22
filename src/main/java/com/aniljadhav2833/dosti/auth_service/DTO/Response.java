package com.aniljadhav2833.dosti.auth_service.DTO;

import java.time.LocalDateTime;

public record Response(String status, String message, LocalDateTime transactionDate) {
}
