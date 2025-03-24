package com.example.product_manager.service.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AuthResponseDto(
    @JsonProperty("token") String token, @JsonProperty("role") List<String> role) {}
