package com.avaliacao.ecommerce.repository;

import com.avaliacao.ecommerce.model.Category;
import com.avaliacao.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);
}
