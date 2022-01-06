package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "TB_ECOM_PRODUTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int code;

    @NotEmpty(message = "Nome do produto não pode ser vazio!")
    @Column(name = "NOME")
    private String name;

    @NotEmpty(message = "Descricao do produto não pode ser vazio!")
    @Column(name = "DESCRICAO")
    private String description;

    @NotEmpty(message = "Marca do produto não pode ser vazio!")
    @Column(name = "MARCA")
    private String brand;

    @NotEmpty(message = "Categoria do produto não pode ser vazio!")
    @OneToOne
    @JoinColumn(name = "CODIGO_CATEGORIA", referencedColumnName = "CODIGO")
    private Category category;

    @NotEmpty(message = "Data cadastro do produto deve ser informada!")
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;

    @NotEmpty(message = "Quantidade do produto deve ser informada!")
    @Column(name = "QUANTIDADE")
    private int quantidade;

    @NotEmpty(message = "Valor do produto deve ser informado!")
    @Column(name = "VALOR")
    private double value;
}
