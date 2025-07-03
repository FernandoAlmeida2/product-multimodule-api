package com.example.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDto(UUID idProduct,
                                 String name,
                                 BigDecimal price,
                                 String category,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt) {
}
