package myhunter.com.controllers;

import org.springframework.web.bind.annotation.*;

import myhunter.com.models.Category;
import myhunter.com.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return service.findAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return service.save(category);
    }
}
