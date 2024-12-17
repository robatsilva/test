package myhunter.com.services;

import org.springframework.stereotype.Service;

import myhunter.com.models.Category;
import myhunter.com.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category save(Category category) {
        return repository.save(category);
    }
}
