package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_ECOM_ENDERECO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int code;

    @Column(name = "TIPO")
    private String type;

    @Column(name = "LOGRADOURO")
    private String publicPlace;

    @Column(name = "NUMERO")
    private Integer number;

    @Column(name = "COMPLEMENTO")
    private String complement;

    @Column(name = "BAIRRO")
    private String district;

    @Column(name = "CEP")
    private String zipCode;

    @Column(name = "CIDADE")
    private String city;

    @Column(name = "ESTADO")
    private String state;

    public Address(String type, String publicPlace, Integer number, String complement, String district, String zipCode, String city, String state) {
        this.type = type;
        this.publicPlace = publicPlace;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }
}
