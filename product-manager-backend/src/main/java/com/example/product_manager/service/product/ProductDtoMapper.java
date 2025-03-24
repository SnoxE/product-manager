package com.example.product_manager.service.product;

import com.example.product_manager.model.ProductEntity;
import com.example.product_manager.service.product.model.ProductDto;
import java.util.List;

public class ProductDtoMapper {

  public static ProductDto toProductDto(ProductEntity entity) {
    return new ProductDto(
        entity.id(), entity.name(), entity.description(), entity.price(), entity.category());
  }

  public static List<ProductDto> toProductDtoList(List<ProductEntity> entities) {
    return entities.stream().map(ProductDtoMapper::toProductDto).toList();
  }
}
