package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.model.Product;
import com.avaliacao.ecommerce.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Produto")
@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Listar")
    @GetMapping
    public List<Product> findAll() {
        return productService.listAllProducts();
    }

    @ApiOperation(value = "Encontrar por codigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Product>> findByCode(@PathVariable Integer codigo) {
        Optional<Product> productResponse = productService.findByCode(codigo);
        return productResponse.isPresent() ? ResponseEntity.ok(productResponse) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar")
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product productRequest) {
        Product productResponse = productService.save(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @ApiOperation(value = "Atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<Product> update(@PathVariable Integer codigo, @RequestBody Product productRequest) {
        return ResponseEntity.ok(productService.update(codigo, productRequest));
    }
}
