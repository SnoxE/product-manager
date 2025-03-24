package com.example.product_manager.service.product;

import com.example.product_manager.common.problem.InternalServerErrorProblem;
import com.example.product_manager.model.ProductEntity;
import com.example.product_manager.repository.ProductRepository;
import com.example.product_manager.service.product.model.NewProductDto;
import com.example.product_manager.service.product.model.ProductDto;
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

  public ProductDto getProductById(String id) {
    var entity = productRepository.findById(id);
    return entity.map(ProductDtoMapper::toProductDto).orElse(null);
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
    }
    catch (Exception e) {
      log.error(
              "Unable to save new product due to an unexpected error, message={}", e.getMessage(), e);
      throw new InternalServerErrorProblem();
    }
  }
}
