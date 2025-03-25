package com.example.product_manager;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class ProductControllerAccessTest {

  @Autowired private MockMvc mockMvc;
  @MockitoBean private ProductService productService;

  private static final String NEW_PRODUCT =
      "{\"name\": \"New Product\", \"description\": \"Description for new product\", \"price\": 100, \"category\": \"Category1\"}";

  @Test
  void testGetProductsAsUnauthenticatedUser() throws Exception {
    mockMvc.perform(get("/api/products")).andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(roles = "USER")
  void testGetProductsAsAuthenticatedUser() throws Exception {
    mockMvc.perform(get("/api/products")).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testGetProductsAsAdmin() throws Exception {
    mockMvc.perform(get("/api/products")).andExpect(status().isOk());
  }

  @Test
  void testPostProductAsUnauthenticatedUser() throws Exception {
    mockMvc
        .perform(post("/api/products").contentType(MediaType.APPLICATION_JSON).content(NEW_PRODUCT))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(roles = "USER")
  void testPostProductAsAuthenticatedUser() throws Exception {
    mockMvc
        .perform(post("/api/products").contentType(MediaType.APPLICATION_JSON).content(NEW_PRODUCT))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testPostProductAsAdmin() throws Exception {
    mockMvc
        .perform(
            post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"name\": \"New Product\", \"description\": \"Description for new product\", \"price\": 100, \"category\": \"Category1\"}")
                .with(csrf()))
        .andExpect(status().isOk());
  }

  @Test
  void testGetProductByIdAsUnauthenticatedUser() throws Exception {
    mockMvc.perform(get("/api/products/{id}", "1")).andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(roles = "USER")
  void testGetProductByIdAsAuthenticatedUser() throws Exception {
    mockMvc.perform(get("/api/products/{id}", "1")).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testGetProductByIdAsAdmin() throws Exception {
    mockMvc.perform(get("/api/products/{id}", "1")).andExpect(status().isOk());
  }

  @Test
  void testPutProductAsUnauthenticatedUser() throws Exception {
    mockMvc
        .perform(
            put("/api/products/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(NEW_PRODUCT))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(roles = "USER")
  void testPutProductAsAuthenticatedUser() throws Exception {
    mockMvc
        .perform(
            put("/api/products/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"name\": \"Updated Product\", \"category\": \"Category1\", \"price\": 150}"))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testPutProductAsAdmin() throws Exception {
    mockMvc
        .perform(
            put("/api/products/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"name\": \"Updated Product\", \"category\": \"Category1\", \"price\": 150}")
                .with(csrf()))
        .andExpect(status().isOk());
  }

  @Test
  void testDeleteProductAsUnauthenticatedUser() throws Exception {
    mockMvc.perform(delete("/api/products/{id}", "1")).andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(roles = "USER")
  void testDeleteProductAsAuthorizedUser() throws Exception {
    mockMvc.perform(delete("/api/products/{id}", "1")).andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void testDeleteProductAsAdmin() throws Exception {
    mockMvc
        .perform(delete("/api/products/{id}", "1").with(csrf()))
        .andExpect(status().isNoContent());
  }

  @Test
  void testGetProductsByCategoryAsUnauthenticatedUser() throws Exception {
    mockMvc
        .perform(get("/api/products/category/{category}", "Smarthphone"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(roles = "USER")
  void testGetProductsByCategoryAsAuthenticatedUser() throws Exception {
    mockMvc
        .perform(get("/api/products/category/{category}", "Category1"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "USER")
  void testGetProductsByCategoryAsAdmin() throws Exception {
    mockMvc
        .perform(get("/api/products/category/{category}", "Category1").with(csrf()))
        .andExpect(status().isOk());
  }
}
