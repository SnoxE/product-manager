package com.example.product_manager;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthRequestDto(
    @JsonProperty("username") String username, @JsonProperty("password") String password) {}
