package com.avaliacao.ecommerce.service;

import com.avaliacao.ecommerce.excecao.BusinessException;
import com.avaliacao.ecommerce.interfaces.ICategoryService;
import com.avaliacao.ecommerce.model.Category;
import com.avaliacao.ecommerce.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Cacheable(value= "allCategoryCache", unless= "#result.size() == 0")
    public List<Category> findAll()
    {
        List<Category> lista = new ArrayList<>();
        categoryRepository.findAll().forEach(e -> lista.add(e));
        return lista;
    }

    @Cacheable(value= "categoryCache", key= "#code")
    public Optional<Category> findByCode(int code) {
        return categoryRepository.findById(code);
    }

    @Override
    @Caching(
        put= { @CachePut(value= "categoryCache", key= "#category.code") },
        evict= { @CacheEvict(value= "allCategoryCache", allEntries= true) }
    )
    public Category save(Category category) {
        this.validateDuplicate(category);
        return categoryRepository.save(category);
    }

    @Override
    @Caching(
        put= { @CachePut(value= "categoryCache", key= "#category.code") },
        evict= { @CacheEvict(value= "allCategoryCache", allEntries= true) }
    )
    public Category update(int code, Category category) {
        Category categoryModel = this.isExists(code);
        this.validateDuplicate(category);
        BeanUtils.copyProperties(category, categoryModel, "code");
        return categoryRepository.save(categoryModel);
    }

    @Override
    @Caching(
        evict= {
            @CacheEvict(value= "categoryCache", key= "#code"),
            @CacheEvict(value= "allCategoryCache", allEntries= true)
        }
    )
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
        Category categoryModel = categoryRepository.findByName(category.getName());
        if (categoryModel != null &&
                categoryModel.getCode() != category.getCode()) {
            throw new BusinessException(
                    String.format("A category %s já esta cadastrada", category.getName().toUpperCase()));
        }
    }
}
