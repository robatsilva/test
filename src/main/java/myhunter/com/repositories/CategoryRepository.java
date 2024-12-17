package myhunter.com.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import myhunter.com.models.Category;
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
