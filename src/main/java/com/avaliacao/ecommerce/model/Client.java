package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "TB_ECOM_CLIENTE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int code;

    @NotEmpty(message = "Nome do cliente não pode ser vazio!")
    @Column(name = "NOME")
    private String name;

    @NotEmpty(message = "CPF do cliente deve ser informada!")
    @Column(name = "CPF")
    private String cpf;

    @NotEmpty(message = "Celular do cliente deve ser informado!")
    @Column(name = "CELULAR")
    private int cell;

    @NotEmpty(message = "E-mail do cliente deve ser informado!")
    @Column(name = "E_MAIL")
    private String email;

    @OneToMany
    @JoinColumn(name = "CODIGO", referencedColumnName = "CODIGO")
    private List<Address> addresses;

    public Client(String name, String cpf, int cell, String email, List<Address> addresses) {
        this.name = name;
        this.cpf = cpf;
        this.cell = cell;
        this.email = email;
        this.addresses = addresses;
    }
}
