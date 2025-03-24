package com.example.product_manager.service.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDto(
    @JsonProperty("id") String id,
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("price") double price,
    @JsonProperty("category") String category) {}
