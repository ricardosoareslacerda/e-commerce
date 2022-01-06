package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "TB_ECOM_ENDERECO_TIPO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressType {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int code;

    @NotEmpty(message = "Nome do tipo do endereço deve ser informada!")
    @Column(name = "NOME")
    private String name;
}
