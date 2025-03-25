package com.example.product_manager.repository.product;

import com.example.product_manager.repository.product.model.ProductEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

  List<ProductEntity> findByCategory(String category);
}
