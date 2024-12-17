package myhunter.com.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import myhunter.com.controllers.CategoryController;
import myhunter.com.models.Category;
import myhunter.com.services.CategoryService;
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

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
@ContextConfiguration(classes = {CategoryControllerTest.MockConfig.class, TestSecurityConfig.class})
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryService categoryService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public CategoryService categoryService() {
            return Mockito.mock(CategoryService.class);
        }
    }

    @Test
    void shouldCreateCategoryWithAuth() throws Exception {
        Category category = Category.builder().name("Eletrônicos").build();

        Mockito.when(categoryService.save(Mockito.any(Category.class))).thenReturn(category);

        mockMvc.perform(post("/categories")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Eletrônicos"));
    }

    @Test
    void shouldGetAllCategoriesWithAuth() throws Exception {
        Category category = Category.builder().id(1L).name("Eletrônicos").build();

        Mockito.when(categoryService.findAll()).thenReturn(Collections.singletonList(category));

        mockMvc.perform(get("/categories")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin123"))) // Autenticação
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Eletrônicos"));
    }
}
