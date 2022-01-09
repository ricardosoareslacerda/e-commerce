package com.avaliacao.ecommerce.service;

import com.avaliacao.ecommerce.excecao.BusinessException;
import com.avaliacao.ecommerce.model.Product;
import com.avaliacao.ecommerce.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> findAll(int code) {
        return productRepository.findByCategoryCode(code);
    }

    public Optional<Product> findByCode(int code, int codeCategory) {
        return productRepository.findByCode(code, codeCategory);
    }

    public Product save(int codeCategory, Product product) {
        this.validateProductCategoryExist(codeCategory);
        this.validateDuplicate(product);
        return productRepository.save(product);
    }

    public Product update(int codeCategory, int code, Product product) {
        Product productModel = this.validateExists(code, codeCategory);
        this.validateProductCategoryExist(codeCategory);
        this.validateDuplicate(product);
        BeanUtils.copyProperties(product, productModel, "code");
        return productRepository.save(productModel);
    }

    public void delete(int codeCategory, int code) {
        Product productModel = this.validateExists(codeCategory, code);
        productRepository.delete(productModel);
    }

    protected void updateQuantityStock(Product product) {
        productRepository.save(product);
    }

    private Product validateExists(int code) {
        Optional<Product> product = productRepository.findById(code);
        if (product.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return product.get();
    }

    private Product validateExists(int code, int codeCategory) {
        Optional<Product> product = this.findByCode(code, codeCategory);
        if (product.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return product.get();
    }

    private void validateDuplicate(Product product) {
        Optional<Product> productModel = productRepository.findByCategoryCodeAndDescription(product.getCode(), product.getDescription());
        if(productModel.isPresent() && productModel.get().getCode() != productModel.get().getCode()) {
            throw new BusinessException(String.format("O produto %s já está cadastrado", productModel.get().getDescription()));
        }
    }

    private void validateProductCategoryExist(int codeCategory) {
        if(codeCategory == 0) {
            throw new BusinessException("A categoria não pode ser nula ou zerada!");
        }

        if(categoryService.findByCode(codeCategory).isEmpty()) {
            throw new BusinessException(String.format("A categoria de código %s informada não existe no cadastro", codeCategory));
        }
    }
}
