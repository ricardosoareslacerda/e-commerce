package com.avaliacao.ecommerce.controller.dto.client;

import com.avaliacao.ecommerce.controller.dto.address.AddressResponseDTO;
import com.avaliacao.ecommerce.model.Client;
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
@ApiModel("Cliente Retorno DTO")
public class ClientResponseDTO {

    @ApiModelProperty(value = "Código")
    private Integer codigo;

    @ApiModelProperty(value = "Nome")
    private String name;

    @ApiModelProperty(value = "CPF")
    private Long cpf;

    @ApiModelProperty(value = "Celular")
    private Long cell;

    @ApiModelProperty(value = "E-mail")
    private String email;

    @ApiModelProperty(value = "Endereço")
    private List<AddressResponseDTO> addresses;

    public ClientResponseDTO(Integer codigo, String name, Long cpf, Long cell, String email) {
        this.codigo = codigo;
        this.name = name;
        this.cpf = cpf;
        this.cell = cell;
        this.email = email;
    }

    public static ClientResponseDTO converterToClientDTO(Client client) {
        return new ClientResponseDTO(client.getCode(),
                client.getName(),
                client.getCpf(),
                client.getCell(),
                client.getEmail());
    }
}
