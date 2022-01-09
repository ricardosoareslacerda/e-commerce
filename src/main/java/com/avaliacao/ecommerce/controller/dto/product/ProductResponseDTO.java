package com.avaliacao.ecommerce.controller.dto.product;

import com.avaliacao.ecommerce.controller.dto.category.CategoryResponseDTO;
import com.avaliacao.ecommerce.model.Product;
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
@ApiModel("Produto Retorno DTO")
public class ProductResponseDTO {

    @ApiModelProperty(value = "Código")
    private int code;

    @ApiModelProperty(value = "Nome")
    private String name;

    @ApiModelProperty(value = "Descrição")
    private String description;

    @ApiModelProperty(value = "Quantidade")
    private int quantity;

    @ApiModelProperty(value = "Valor")
    private double value;

    @ApiModelProperty(value = "Categoria")
    private CategoryResponseDTO category;

    public ProductResponseDTO converterToProductDTO(Product product) {
        return new ProductResponseDTO(product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getValue(),
                CategoryResponseDTO.converterToCategoryDTO(product.getCategory()));

    }
}
