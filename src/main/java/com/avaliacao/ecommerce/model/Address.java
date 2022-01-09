package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TB_ECOM_ENDERECO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_CLIENTE", referencedColumnName = "CODIGO")
    private Client client;

    public Address(String type, String publicPlace, Integer number, String complement, String district, String zipCode, String city, String state, Client client) {
        this.type = type;
        this.publicPlace = publicPlace;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.client = client;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type, publicPlace, number, complement, district, zipCode, city, state);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }
        Address other = (Address) obj;
        return Objects.equals(code, other.code)
                && Objects.equals(type, other.type)
                && Objects.equals(publicPlace, other.publicPlace)
                && Objects.equals(number, other.number)
                && Objects.equals(complement, other.complement)
                && Objects.equals(district, other.district)
                && Objects.equals(zipCode, other.zipCode)
                && Objects.equals(city, other.city)
                && Objects.equals(state, other.state);
    }
}
