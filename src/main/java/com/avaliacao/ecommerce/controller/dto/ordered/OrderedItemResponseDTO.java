package com.avaliacao.ecommerce.controller.dto.ordered;

import com.avaliacao.ecommerce.controller.dto.product.ProductResponseDTO;
import com.avaliacao.ecommerce.model.OrderedItem;
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
@ApiModel("Pedido Item Retorno DTO")
public class OrderedItemResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private int code;

    @ApiModelProperty(value = "Produto")
    private ProductResponseDTO product;

    @ApiModelProperty(value = "Quantidade")
    private int quantity;

    public static OrderedItemResponseDTO converterToOrderedItemResponseDTO(OrderedItem orderedItem) {
        return new OrderedItemResponseDTO(orderedItem.getCode(),
                ProductResponseDTO.converterToProductDTO(orderedItem.getProduct()),
                orderedItem.getQuantity());
    }
}
