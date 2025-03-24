package com.example.product_manager.service.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public record UpdateProductDto(
    @JsonProperty("name") @NotEmpty String name,
    @JsonProperty("description") @NotEmpty String description,
    @JsonProperty("price") @NotEmpty double price,
    @JsonProperty("category") @NotEmpty String category) {}
