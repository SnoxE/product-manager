package com.example.product_manager.service.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;

public record UpdateProductDto(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("price") @Min(0) double price,
    @JsonProperty("category") String category) {}
