package com.avaliacao.ecommerce.controller.dto.address;

import com.avaliacao.ecommerce.model.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Endereço Requisicao DTO")
public class AddressRequestDTO {

    @ApiModelProperty(value = "Tipo")
    @NotBlank(message = "Tipo do endereço não pode ser vazio!")
    private String type;

    @ApiModelProperty(value = "Logradouro")
    @NotBlank(message = "Logradouro do endereço deve ser informada!")
    private String publicPlace;

    @ApiModelProperty(value = "Número")
    @NotBlank(message = "Número do endereço deve ser informada!")
    private Integer number;

    @ApiModelProperty(value = "Complemento")
    @NotEmpty(message = "Complemento do endereço deve ser informado!")
    private String complement;

    @ApiModelProperty(value = "Bairro")
    @NotEmpty(message = "Bairro do endereço deve ser informado!")
    private String district;

    @ApiModelProperty(value = "CEP")
    @NotEmpty(message = "Cep do endereço deve ser informado!")
    private String zipCode;

    @ApiModelProperty(value = "Cidade")
    @NotEmpty(message = "Cidade do endereço deve ser informado!")
    private String city;

    @ApiModelProperty(value = "Estado")
    @NotEmpty(message = "Estado do endereço deve ser informado!")
    private String state;

    public Address converterAddressModel() {
        return new Address(type, publicPlace, number, complement, district, zipCode, city, state);
    }

    public Address converterAddressModel(int code) {
        return new Address(code, type, publicPlace, number, complement, district, zipCode, city, state);
    }
}
