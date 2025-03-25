package com.example.product_manager.repository.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record UserEntity(
    @Id String id, String username, String email, String password, String role) {}
