package com.example.product_manager.service.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthRequestDto(
    @JsonProperty("username") @NotEmpty @Size(min = 3, max = 32) String username,
    @JsonProperty("password") @NotEmpty @Size(min = 8) String password) {}
