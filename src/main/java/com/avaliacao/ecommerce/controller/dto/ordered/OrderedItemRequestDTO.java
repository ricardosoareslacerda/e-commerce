package com.avaliacao.ecommerce.controller.dto.ordered;

import com.avaliacao.ecommerce.model.Ordered;
import com.avaliacao.ecommerce.model.OrderedItem;
import com.avaliacao.ecommerce.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Pedido Item Requisicao DTO")
public class OrderedItemRequestDTO {

    @ApiModelProperty(value = "Pedido")
    @NotNull(message = "Pedido não pode ser vazio!")
    private OrderedRequestDTO ordered;

    @ApiModelProperty(value = "Produto")
    @NotNull(message = "Produto do pedido não pode ser vazio!")
    //@Min(value = 1, message = "Produto")
    private int productCode;

    @ApiModelProperty(value = "Endereço de Entrega")
    @NotNull(message = "Categoria do produto não pode ser vazio!")
    //@Min(value = 1, message = "Categoria do produto")
    private int categoryCode;

    @ApiModelProperty(value = "Total")
    @NotNull(message = "Total do pedido não pode ser vazio!")
    private int quantity;

    public OrderedItem converterOrderedItemModel(Ordered ordered, Product product, int quantity) {
        return new OrderedItem(ordered, product, quantity);
    }
}
