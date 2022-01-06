package com.avaliacao.ecommerce.controller.dto;

import com.avaliacao.ecommerce.model.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Categoria Retorno DTO")
public class CategoryResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private int code;

    @ApiModelProperty(value = "Nome")
    private String name;

    public static CategoryResponseDTO converterCategoryDTO(Category category) {
        return new CategoryResponseDTO(category.getCode(), category.getName());
    }
}
