package com.example.product_manager;

import com.example.product_manager.common.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ProductManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductManagerApplication.class, args);
  }
}
