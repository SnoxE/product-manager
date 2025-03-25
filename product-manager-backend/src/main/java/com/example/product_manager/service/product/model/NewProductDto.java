package com.example.product_manager.service.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewProductDto(
    @JsonProperty("name") @NotEmpty @Size(min = 2, max = 64) String name,
    @JsonProperty("description") @NotEmpty @Size(min = 2, max = 128) String description,
    @JsonProperty("price") @Min(0) double price,
    @JsonProperty("category") @NotEmpty @Size(min = 2, max = 64) String category) {}
