package myhunter.com.controllers;

import org.springframework.web.bind.annotation.*;

import myhunter.com.models.Product;
import myhunter.com.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.findAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return service.save(product);
    }
}
