package com.avaliacao.ecommerce.repository;

import com.avaliacao.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryCode(int code);

    @Query("Select prod"
            + " from Product prod"
            + " where prod.code = :code"
            + "   and prod.category.code = :codeCategory")
    Optional<Product> findByCode(int code, int codeCategory);

    Optional<Product> findByCategoryCodeAndDescription(int codeCategory, String description);
}
