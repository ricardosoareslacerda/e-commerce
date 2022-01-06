package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_ECOM_PEDIDO_ITEM")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderedItem {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int code;

    @ManyToOne
    @JoinColumn(name = "CODIGO_PEDIDO", referencedColumnName = "CODIGO")
    private Ordered ordered;

    @OneToOne
    @JoinColumn(name = "CODIGO_PRODUTO", referencedColumnName = "CODIGO")
    private Product product;

    @Column(name = "QUANTIDADE")
    private int quantity;
}
