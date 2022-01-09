package com.avaliacao.ecommerce.controller.dto.ordered;

import com.avaliacao.ecommerce.controller.dto.product.ProductResponseDTO;
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
public class OrderedItemResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private int code;

    @ApiModelProperty(value = "Pedido")
    private OrderedResponseDTO ordered;

    @ApiModelProperty(value = "Produto")
    private ProductResponseDTO product;

    @ApiModelProperty(value = "Total")
    private int quantity;
}
