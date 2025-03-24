package com.example.product_manager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public record ProductEntity(
    @Id String id, String name, String description, Double price, String category) {}
