package com.avaliacao.ecommerce.controller.dto.ordered;

import com.avaliacao.ecommerce.controller.dto.address.AddressRequestDTO;
import com.avaliacao.ecommerce.controller.dto.client.ClientRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Categoria Requisicao DTO")
public class OrderedRequestDTO {

    @ApiModelProperty(value = "Data")
    @NotNull(message = "Data do pedido não pode ser vazio!")
    private Date data;

    @ApiModelProperty(value = "Cliente")
    @NotNull(message = "Cliente do pedido não pode ser vazio!")
    @Min(value = 1, message = "Cliente")
    private ClientRequestDTO client;

    @ApiModelProperty(value = "Endereço de Entrega")
    @NotNull(message = "Endereço de Entrega do pedido não pode ser vazio!")
    @Min(value = 1, message = "Endereço de Entrega")
    private AddressRequestDTO delivery;

    @ApiModelProperty(value = "Itens do pedido")
    @NotEmpty(message = "Itens do pedido não pode ser vazio!")
    @Min(value = 1, message = "Itens do pedido")
    private List<OrderedItemRequestDTO> itens;

    @ApiModelProperty(value = "Total do pedido")
    @NotNull(message = "Total do pedido não pode ser vazio!")
    private double amount;
}
