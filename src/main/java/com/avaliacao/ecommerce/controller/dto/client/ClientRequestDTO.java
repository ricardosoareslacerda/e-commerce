package com.avaliacao.ecommerce.controller.dto.client;

import com.avaliacao.ecommerce.controller.dto.address.AddressRequestDTO;
import com.avaliacao.ecommerce.model.Client;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Cliente Requisicao DTO")
public class ClientRequestDTO {

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "Nome da categoria deve ser informada!")
    @Length(min = 3, max = 50, message = "Nome")
    private String name;

    @ApiModelProperty(value = "CPF")
    @NotBlank(message = "CPF do cliente deve ser informada!")
    private String cpf;

    @ApiModelProperty(value = "Celular")
    @NotBlank(message = "Celular do cliente deve ser informado!")
    @Pattern(regexp = "\\([\\d]{2}\\)[\\d]{5}[- .][\\d]{4}", message = "Celular")
    private int cell;

    @ApiModelProperty(value = "E-mail")
    @NotEmpty(message = "E-mail do cliente deve ser informado!")
    private String email;

    @ApiModelProperty(value = "Endereço")
    @NotNull(message = "Endereço")
    @Valid
    private List<AddressRequestDTO> addresses;

    public Client converterClientModel() {
        return new Client(name, cpf, cell, email, addresses.stream().map(addressDTO -> addressDTO.converterAddressModel()).collect(Collectors.toList()));
    }

    public Client converterClientModel(int code) {
        return new Client(name, cpf, cell, email, addresses.stream().map(addressDTO -> addressDTO.converterAddressModel(code)).collect(Collectors.toList()));
    }
}
