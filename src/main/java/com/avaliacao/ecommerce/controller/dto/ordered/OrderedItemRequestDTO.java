package com.avaliacao.ecommerce.controller.dto.ordered;

import com.avaliacao.ecommerce.controller.dto.product.ProductRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Categoria Requisicao DTO")
public class OrderedItemRequestDTO {

    @ApiModelProperty(value = "Pedido")
    @NotNull(message = "Pedido não pode ser vazio!")
    private OrderedRequestDTO ordered;

    @ApiModelProperty(value = "Produto")
    @NotNull(message = "Produto do pedido não pode ser vazio!")
    @Min(value = 1, message = "Produto")
    private ProductRequestDTO product;

    @ApiModelProperty(value = "Total")
    @NotNull(message = "Total do pedido não pode ser vazio!")
    private int quantity;
}
