package com.example.product_manager.repository.user;

import com.example.product_manager.repository.user.model.UserEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
  Optional<UserEntity> findByUsername(String username);
}
