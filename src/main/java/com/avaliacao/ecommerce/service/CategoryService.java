package com.avaliacao.ecommerce.service;

import com.avaliacao.ecommerce.excecao.BusinessException;
import com.avaliacao.ecommerce.model.Category;
import com.avaliacao.ecommerce.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findByCode(int code) {
        return categoryRepository.findById(code);
    }

    public Category save(Category category) {
        this.validateDuplicate(category);
        return categoryRepository.save(category);
    }

    public Category update(int code, Category category) {
        Category categoryModel = this.isExists(code);
        this.validateDuplicate(category);
        BeanUtils.copyProperties(category, categoryModel, "code");
        return categoryRepository.save(categoryModel);
    }

    public void delete(Integer code) {
        categoryRepository.deleteById(code);
    }

    private Category isExists(int code) {
        Optional<Category> category = this.findByCode(code);
        if (category.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return category.get();
    }

    private void validateDuplicate(Category category) {
        Category categoryModel = categoryRepository.findByNome(category.getName());
        if (categoryModel != null &&
                categoryModel.getCode() != category.getCode()) {
            throw new BusinessException(
                    String.format("A category %s já esta cadastrada", category.getName().toUpperCase()));
        }
    }
}
