package com.example.product_manager;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponseDto(
    @JsonProperty("token") String token, @JsonProperty("role") java.util.List<String> role) {}
