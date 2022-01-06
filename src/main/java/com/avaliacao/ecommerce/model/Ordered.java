package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_ECOM_PEDIDO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ordered {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int code;

    @NotEmpty(message = "Data do pedido não pode ser vazio!")
    @Column(name = "Data")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "CODIGO_CLIENTE", referencedColumnName = "CODIGO")
    private Client client;

    @OneToOne
    @JoinColumn(name = "CODIGO_ENTREGA", referencedColumnName = "CODIGO")
    private Address delivery;

    @OneToMany
    @JoinColumn(name = "CODIGO_ITEM", referencedColumnName = "CODIGO")
    private List<OrderedItem> itens;

    @NotEmpty(message = "Total do pedido deve ser informado!")
    @Column(name = "TOTAL")
    private double amount;
}
