package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.controller.dto.product.ProductRequestDTO;
import com.avaliacao.ecommerce.controller.dto.product.ProductResponseDTO;
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
import java.util.stream.Collectors;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Listar", nickname = "findAll")
    @GetMapping
    public List<ProductResponseDTO> findAll(@PathVariable Integer codigoCategoria) {
        return productService.findAll(codigoCategoria)
                .stream()
                .map(productModel -> ProductResponseDTO.builder().build()
                        .converterToProductDTO(productModel))
                            .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por codigo", nickname = "findByCode")
    @GetMapping("/{codigo}")
    public ResponseEntity<ProductResponseDTO> findByCode(@PathVariable Integer codigoCategoria,
                                                                   @PathVariable Integer codigo) {
        Optional<Product> productModel = productService.findByCode(codigo, codigoCategoria);
        return productModel.isPresent()
                ? ResponseEntity.ok(ProductResponseDTO.builder().build().converterToProductDTO(productModel.get()))
                : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "save")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@PathVariable Integer codigoCategoria, @RequestBody ProductRequestDTO productRequest) {
        Product productModel = productService.save(codigoCategoria, productRequest.converterProductModel(codigoCategoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDTO.builder().build().converterToProductDTO(productModel));
    }

    @ApiOperation(value = "Atualizar", nickname = "update")
    @PutMapping("/{codigo}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Integer codigoCategoria, @PathVariable Integer codigo, @RequestBody ProductRequestDTO productRequest) {
        return ResponseEntity.ok(ProductResponseDTO.builder().build().converterToProductDTO(productService.update(codigoCategoria, codigo, productRequest.converterProductModel(codigoCategoria, codigo))));
    }

    @ApiOperation(value = "Deletar", nickname = "deletar")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer codigoCategoria, @PathVariable Integer codigo) {
        productService.delete(codigoCategoria, codigo);
    }
}
