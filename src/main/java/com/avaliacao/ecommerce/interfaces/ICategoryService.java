package com.avaliacao.ecommerce.interfaces;

import com.avaliacao.ecommerce.model.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findByCode(int code);
    Category save(Category category);
    Category update(int code, Category category);
    void delete(Integer code);
}