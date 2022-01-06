package com.avaliacao.ecommerce.controller.dto;

import com.avaliacao.ecommerce.model.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Categoria Requisicao DTO")
public class CategoryRequestDTO {

    @ApiModelProperty(value = "Nome")
    @NotEmpty(message = "Nome da categoria deve ser informada!")
    private String name;

    public Category converterCategoryModel() {
        return new Category(name);
    }
}
