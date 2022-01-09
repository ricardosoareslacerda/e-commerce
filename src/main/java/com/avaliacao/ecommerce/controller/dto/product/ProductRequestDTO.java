package com.avaliacao.ecommerce.controller.dto.product;

import com.avaliacao.ecommerce.model.Category;
import com.avaliacao.ecommerce.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Produto Requisicao DTO")
public class ProductRequestDTO {

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "Nome do produto não pode ser vazio!")
    //@Length(min = 3, max = 100, message = "Nome")
    private String name;

    @ApiModelProperty(value = "Descrição")
    @NotBlank(message = "Descricao do produto não pode ser vazio!")
    private String description;

    @ApiModelProperty(value = "Marca")
    @NotBlank(message = "Marca do produto não pode ser vazio!")
    private String brand;

    @ApiModelProperty(value = "Quantidade")
    @NotNull(message = "Quantidade do produto deve ser informada!")
    private int quantity;

    @ApiModelProperty(value = "Valor")
    @NotNull(message = "Valor do produto deve ser informado!")
    @Length(max = 500, message = "Observação")
    private double value;

    public Product converterProductModel(int codeCategory) {
        return new Product(name, description, quantity, value, new Category(codeCategory));
    }

    public Product converterProductModel(int codeCategory, int code) {
        return new Product(code, name, description, quantity, value, new Category(codeCategory));
    }
}
