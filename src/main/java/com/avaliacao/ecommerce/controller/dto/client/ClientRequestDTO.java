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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "CPF do cliente deve ser informada!")
    private Long cpf;

    @ApiModelProperty(value = "Celular")
    @NotNull(message = "Celular do cliente deve ser informado!")
    private Long cell;

    @ApiModelProperty(value = "E-mail")
    @NotEmpty(message = "E-mail do cliente deve ser informado!")
    private String email;/*

    @ApiModelProperty(value = "Endereço")
    @NotNull(message = "Endereço")
    private List<AddressRequestDTO> addresses;*/

   /* public Client converterClientModel() {
        return new Client(name, cpf, cell, email, addresses.stream().map(addressDTO -> addressDTO.converterAddressModel()).collect(Collectors.toList()));
    }*/
   public Client converterClientModel() {
       return new Client(name, cpf, cell, email);
   }

   public Client converterClientModel(int code) {
        return new Client(name, cpf, cell, email);
    }

    /*public Client converterClientModel(int code) {
        return new Client(name, cpf, cell, email, addresses.stream().map(addressDTO -> addressDTO.converterAddressModel(code)).collect(Collectors.toList()));
    }*/
}
