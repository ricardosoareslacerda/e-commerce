package com.avaliacao.ecommerce.controller.dto.ordered;

import com.avaliacao.ecommerce.controller.dto.address.AddressResponseDTO;
import com.avaliacao.ecommerce.controller.dto.client.ClientResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Categoria Retorno DTO")
public class OrderedResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private Integer code;

    @ApiModelProperty(value = "Data")
    private LocalDate data;

    @ApiModelProperty(value = "Cliente")
    private ClientResponseDTO client;

    @ApiModelProperty(value = "Endereço de Entrega")
    private AddressResponseDTO delivery;

    @ApiModelProperty(value = "Itens do pedido")
    private List<OrderedItemRequestDTO> itens;

    @ApiModelProperty(value = "Total do pedido")
    private double amount;
}
