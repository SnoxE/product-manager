package com.example.product_manager;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.product_manager.controller.ProductController;
import com.example.product_manager.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
public class ProductControllerValidityTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ProductService productService;

  @Test
  @WithMockUser(roles = "ADMIN")
  void testShouldFailToCreateNewProductDueToTooShortName() throws Exception {
    String invalidProductJson =
        "{"
            + "\"name\": \"\","
            + "\"description\": \"Description for new product\","
            + "\"price\": 100,"
            + "\"category\": \"Category1\""
            + "}";

    mockMvc
        .perform(
            post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidProductJson)
                .with(csrf()))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testShouldFailToCreateNewProductDueToTooShortDescription() throws Exception {
    String invalidProductJson =
        "{"
            + "\"name\": \"Valid Name\","
            + "\"description\": \"\","
            + "\"price\": 100,"
            + "\"category\": \"Category1\""
            + "}";

    mockMvc
        .perform(
            post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidProductJson)
                .with(csrf()))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testShouldFailToCreateNewProductDueToNegativePrice() throws Exception {
    String invalidProductJson =
        "{"
            + "\"name\": \"Valid Name\","
            + "\"description\": \"Description for new product\","
            + "\"price\": -100,"
            + "\"category\": \"Category1\""
            + "}";

    mockMvc
        .perform(
            post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidProductJson)
                .with(csrf()))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testShouldFailToCreateNewProductDueToTooShortCategoryName() throws Exception {
    String invalidProductJson =
        "{"
            + "\"name\": \"Valid Name\","
            + "\"description\": \"Description for new product\","
            + "\"price\": -100,"
            + "\"category\": \"\""
            + "}";

    mockMvc
        .perform(
            post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidProductJson)
                .with(csrf()))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testShouldCreateNewProduct() throws Exception {
    String validProductJson =
        "{"
            + "\"name\": \"Valid Product\","
            + "\"description\": \"Valid description\","
            + "\"price\": 100,"
            + "\"category\": \"Category1\""
            + "}";

    mockMvc
        .perform(
            post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validProductJson)
                .with(csrf()))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testShouldFailToUpdateProductDueToNegativePrice() throws Exception {
    String invalidProductJson =
        "{"
            + "\"name\": \"Valid Name\","
            + "\"description\": \"Description\","
            + "\"price\": -1,"
            + "\"category\": \"Valid Category\""
            + "}";

    mockMvc
        .perform(
            put("/api/products/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidProductJson)
                .with(csrf()))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testShouldUpdateProduct() throws Exception {
    String validProductJson =
        "{"
            + "\"name\": \"Updated Product\","
            + "\"description\": \"Updated description\","
            + "\"price\": 150,"
            + "\"category\": \"Updated Category\""
            + "}";

    mockMvc
        .perform(
            put("/api/products/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validProductJson)
                .with(csrf()))
        .andExpect(status().isOk());
  }
}
