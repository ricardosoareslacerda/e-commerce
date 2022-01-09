package com.avaliacao.ecommerce.controller.dto.ordered;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Cliente / Pedido Requisicao DTO")
public class ClientOrderedResponseDTO {

    @ApiModelProperty(value = "Nome cliente")
    private String nome;

    @ApiModelProperty(value = "Pedido")
    private List<OrderedResponseDTO> orderedResponseDTOS;
}
