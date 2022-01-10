package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.controller.dto.category.CategoryRequestDTO;
import com.avaliacao.ecommerce.interfaces.ICategoryService;
import com.avaliacao.ecommerce.model.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @ApiOperation(value = "Listar", nickname = "findAll")
    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @ApiOperation(value = "Listar por código", nickname = "findByCode")
    @GetMapping("/{codigo}")
    public ResponseEntity<Category> findByCode(@PathVariable Integer codigo) {
        Optional<Category> category = categoryService.findByCode(codigo);
       return category.isPresent()
                ? ResponseEntity.ok(category.get())
                : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "save")
    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody CategoryRequestDTO categoryTypeRequest) {
        Category category = categoryService.save(categoryTypeRequest.converterCategoryModel());
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @ApiOperation(value = "Atualizar", nickname = "update")
    @PutMapping("/{codigo}")
    public Category update(@PathVariable Integer codigo, @Valid @RequestBody CategoryRequestDTO categoryTypeRequest) {
        return categoryService.update(codigo, categoryTypeRequest.converterCategoryModel(codigo));
    }

    @ApiOperation(value = "Deletar", nickname = "delete")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer codigo) {
        categoryService.delete(codigo);
    }
}
