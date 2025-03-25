package com.example.product_manager.service.product;

import com.example.product_manager.common.problem.InternalServerErrorProblem;
import com.example.product_manager.common.problem.NotFoundProblem;
import com.example.product_manager.repository.product.ProductRepository;
import com.example.product_manager.repository.product.model.ProductEntity;
import com.example.product_manager.service.product.model.NewProductDto;
import com.example.product_manager.service.product.model.ProductDto;
import com.example.product_manager.service.product.model.UpdateProductDto;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private static final Logger log = LoggerFactory.getLogger(ProductService.class);

  ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<ProductDto> getAllProducts() {
    List<ProductEntity> productEntityList = productRepository.findAll();
    return ProductDtoMapper.toProductDtoList(productEntityList);
  }

  public ProductDto addProduct(NewProductDto productDto) {
    ProductEntity entity =
        new ProductEntity(
            null,
            productDto.name(),
            productDto.description(),
            productDto.price(),
            productDto.category());

    try {
      entity = productRepository.save(entity);
      return ProductDtoMapper.toProductDto(entity);
    } catch (Exception e) {
      log.error(
          "Unable to save new product due to an unexpected error, message={}", e.getMessage(), e);
      throw new InternalServerErrorProblem();
    }
  }

  public ProductDto getProductById(String id) {
    var entity = productRepository.findById(id);
    return entity.map(ProductDtoMapper::toProductDto).orElse(null);
  }

  public ProductDto updateProduct(String id, UpdateProductDto productDto) {
    var updatedEntity =
        new ProductEntity(
            id,
            productDto.name(),
            productDto.description(),
            productDto.price(),
            productDto.category());

    try {
      return ProductDtoMapper.toProductDto(productRepository.save(updatedEntity));
    } catch (Exception e) {
      log.error(
          "Unable to update product due to an unexpected error, message={}", e.getMessage(), e);
      throw new InternalServerErrorProblem();
    }
  }

  public void deleteProduct(String id) {
    if (!productRepository.existsById(id)) {
      throw new NotFoundProblem("Product with id '" + id + "' not found");
    }

    try {
      productRepository.deleteById(id);
    } catch (Exception e) {
      log.error(
          "Unable to delete product due to an unexpected error, message={}", e.getMessage(), e);
      throw new InternalServerErrorProblem();
    }
  }

  public List<ProductDto> getProductsByCategory(String category) {
    List<ProductEntity> productEntityList = productRepository.findByCategory(category);
    return ProductDtoMapper.toProductDtoList(productEntityList);
  }
}
