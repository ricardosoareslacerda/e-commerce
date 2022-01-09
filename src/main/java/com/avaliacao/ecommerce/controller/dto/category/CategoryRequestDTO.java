package com.avaliacao.ecommerce.controller.dto.category;

import com.avaliacao.ecommerce.model.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Categoria Requisicao DTO")
public class CategoryRequestDTO {

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "Nome da categoria deve ser informada!")
    @Length(min = 3, max = 50, message = "Nome")
    private String name;

    public Category converterCategoryModel() {
        return new Category(name);
    }

    public Category converterCategoryModel(int code) {
        return new Category(code, name);
    }
}
