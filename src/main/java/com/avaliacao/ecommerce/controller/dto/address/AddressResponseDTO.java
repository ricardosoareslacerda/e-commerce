package com.avaliacao.ecommerce.controller.dto.address;

import com.avaliacao.ecommerce.model.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Categoria Retorno DTO")
public class AddressResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private int code;

    @ApiModelProperty(value = "Tipo")
    private String type;

    @ApiModelProperty(value = "Logradouro")
    private String publicPlace;

    @ApiModelProperty(value = "Número")
    private Integer number;

    @ApiModelProperty(value = "Complemento")
    private String complement;

    @ApiModelProperty(value = "Bairro")
    private String district;

    @ApiModelProperty(value = "CEP")
    private String zipCode;

    @ApiModelProperty(value = "Cidade")
    private String city;

    @ApiModelProperty(value = "Estado")
    private String state;

    public static AddressResponseDTO converterToAddresResponseDTO(Address addressModel) {
        return new AddressResponseDTO(addressModel.getCode(),
                                      addressModel.getType(),
                                      addressModel.getPublicPlace(),
                                      addressModel.getNumber(),
                                      addressModel.getComplement(),
                                      addressModel.getDistrict(),
                                      addressModel.getZipCode(),
                                      addressModel.getCity(),
                                      addressModel.getState());
    }
}
