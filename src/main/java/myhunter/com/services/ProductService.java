package myhunter.com.services;

import org.springframework.stereotype.Service;

import myhunter.com.models.Product;
import myhunter.com.repositories.ProductRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findWithFilters(String name, BigDecimal minPrice, BigDecimal maxPrice, String sortBy,
            String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return repository.findWithFilters(name, minPrice, maxPrice, sort);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
