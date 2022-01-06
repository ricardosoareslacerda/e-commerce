package com.avaliacao.ecommerce.service;

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

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findByCode(int code) {
        return productRepository.findById(code);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(int code, Product product) {
        Product productModel = this.isExists(code);
        BeanUtils.copyProperties(product, productModel, "code");
        return productRepository.save(productModel);
    }

    private Product isExists(int code) {
        Optional<Product> product = this.findByCode(code);
        if (product.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return product.get();
    }
}
