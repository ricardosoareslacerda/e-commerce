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
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Categoria Retorno DTO")
public class ClientResponseDTO {

    @ApiModelProperty(value = "Código")
    private Integer codigo;

    @ApiModelProperty(value = "Nome")
    private String name;

    @ApiModelProperty(value = "CPF")
    private String cpf;

    @ApiModelProperty(value = "Celular")
    private int cell;

    @ApiModelProperty(value = "E-mail")
    private String email;

    @ApiModelProperty(value = "Endereço")
    private List<AddressResponseDTO> addresses;

    public static ClientResponseDTO converterToClientDTO(Client client) {
        return new ClientResponseDTO(client.getCode(),
                                    client.getName(),
                                    client.getCpf(),
                                    client.getCell(),
                                    client.getEmail(),
                                    client.getAddresses().stream().map(addressModel -> AddressResponseDTO.converterToAddresResponseDTO(addressModel)).collect(Collectors.toList()));
    }
}
