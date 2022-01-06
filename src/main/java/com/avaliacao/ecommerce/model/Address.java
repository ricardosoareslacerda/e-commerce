package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    @NotEmpty(message = "Nome do endereço não pode ser vazio!")
    @Column(name = "NOME")
    private String name;

    @OneToOne
    @JoinColumn(name = "CODIGO_TIPO", referencedColumnName = "CODIGO")
    @NotEmpty(message = "Tipo do endereço não pode ser vazio!")
    private AddressType type;

    @NotEmpty(message = "Logradouro do cliente deve ser informada!")
    @Column(name = "LOGRADOURO")
    private String publicPlace;

    @NotEmpty(message = "Complemento do cliente deve ser informado!")
    @Column(name = "COMPLEMENTO")
    private String complement;
}
