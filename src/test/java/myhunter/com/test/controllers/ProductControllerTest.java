package myhunter.com.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import myhunter.com.controllers.ProductController;
import myhunter.com.models.Category;
import myhunter.com.models.Product;
import myhunter.com.services.ProductService;
import myhunter.com.test.config.TestSecurityConfig;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = {ProductControllerTest.MockConfig.class, TestSecurityConfig.class})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public ProductService productService() {
            return Mockito.mock(ProductService.class);
        }
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        Category category = Category.builder()
                .id(1L)
                .name("Eletrônicos")
                .build();

        Product product = Product.builder()
                .id(1L)
                .name("Smartphone")
                .description("Smartphone com 128GB")
                .price(new BigDecimal("2999.90"))
                .category(category)
                .available(true)
                .build();

        Mockito.when(productService.findAll()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/api/products")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Smartphone"))
                .andExpect(jsonPath("$[0].category.name").value("Eletrônicos"));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        Category category = Category.builder()
                .id(1L)
                .build();

        Product product = Product.builder()
                .name("Smartphone")
                .description("Smartphone com 128GB")
                .price(new BigDecimal("2999.90"))
                .category(category)
                .available(true)
                .build();

        Mockito.when(productService.save(Mockito.any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Smartphone"));
    }
}
