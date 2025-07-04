package com.example.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRecordDto(@NotBlank String name) {
}
