package com.avaliacao.ecommerce.controller.dto.ordered;

import com.avaliacao.ecommerce.model.Address;
import com.avaliacao.ecommerce.model.Client;
import com.avaliacao.ecommerce.model.Ordered;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Pedido Requisicao DTO")
public class OrderedRequestDTO {

    @ApiModelProperty(value = "Cliente")
    @NotNull(message = "Cliente do pedido não pode ser vazio!")
    //@Min(value = 1, message = "Cliente")
    private int clientCode;

    @ApiModelProperty(value = "Endereço de Entrega")
    @NotNull(message = "Endereço de Entrega do pedido não pode ser vazio!")
    //@Min(value = 1, message = "Endereço de Entrega")
    private int deliveryCode;

    @ApiModelProperty(value = "Itens do pedido")
    @NotEmpty(message = "Itens do pedido não pode ser vazio!")
    //@Min(value = 1, message = "Itens do pedido")
    private List<OrderedItemRequestDTO> itens;

    @ApiModelProperty(value = "Total do pedido")
    @NotNull(message = "Total do pedido não pode ser vazio!")
    private double amount;

    public Ordered converterOrderedModel(Client client, Address delivery, double amount) {
        return new Ordered(LocalDate.now(), client, delivery, amount);
    }
}
