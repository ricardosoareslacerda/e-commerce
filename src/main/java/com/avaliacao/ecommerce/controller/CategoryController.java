package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.controller.dto.category.CategoryRequestDTO;
import com.avaliacao.ecommerce.controller.dto.category.CategoryResponseDTO;
import com.avaliacao.ecommerce.model.Category;
import com.avaliacao.ecommerce.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Listar", nickname = "findAll")
    @GetMapping
    public List<CategoryResponseDTO> findAll() {
        return categoryService.findAll().stream()
                        .map(category -> CategoryResponseDTO.converterToCategoryDTO(category))
                        .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por código", nickname = "findByCode")
    @GetMapping("/{codigo}")
    public ResponseEntity<CategoryResponseDTO> findByCode(@PathVariable Integer codigo) {
        Optional<Category> categoryResponse = categoryService.findByCode(codigo);
        return categoryResponse.isPresent()
                ? ResponseEntity.ok(CategoryResponseDTO.converterToCategoryDTO(categoryResponse.get()))
                : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "save")
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@Valid @RequestBody CategoryRequestDTO categoryTypeRequest) {
        Category categoryResponse = categoryService.save(categoryTypeRequest.converterCategoryModel());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryResponseDTO.converterToCategoryDTO(categoryResponse));
    }

    @ApiOperation(value = "Atualizar", nickname = "update")
    @PutMapping("/{codigo}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Integer codigo, @Valid @RequestBody CategoryRequestDTO categoryTypeRequest) {
        return ResponseEntity.ok(CategoryResponseDTO.converterToCategoryDTO(categoryService.update(codigo, categoryTypeRequest.converterCategoryModel(codigo))));
    }

    @ApiOperation(value = "Deletar", nickname = "delete")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer codigo) {
        categoryService.delete(codigo);
    }
}
