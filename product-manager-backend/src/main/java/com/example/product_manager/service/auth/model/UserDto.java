package com.example.product_manager.service.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(
    @JsonProperty("id") String id,
    @JsonProperty("username") String username,
    @JsonProperty("role") String role) {}
