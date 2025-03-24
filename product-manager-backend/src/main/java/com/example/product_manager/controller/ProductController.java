package com.example.product_manager.controller;

import com.example.product_manager.service.product.ProductService;
import com.example.product_manager.service.product.model.NewProductDto;
import com.example.product_manager.service.product.model.ProductDto;
import com.example.product_manager.service.product.model.UpdateProductDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ProductDto> getProducts() {
    return productService.getAllProducts();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ProductDto getProductDetails(@PathVariable @NotEmpty String id) {
    return productService.getProductById(id);
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ProductDto addProduct(@RequestBody @Valid NewProductDto productDto) {
    return productService.addProduct(productDto);
  }

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ProductDto updateProduct(
      @PathVariable @NotEmpty String id, @RequestBody @Valid UpdateProductDto productDto) {
    return productService.updateProduct(id, productDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable @NotEmpty String id) {
    productService.deleteProduct(id);
  }
}
