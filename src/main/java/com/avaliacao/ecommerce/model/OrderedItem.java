package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TB_ECOM_PEDIDO_ITEM")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderedItem {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_PEDIDO", referencedColumnName = "CODIGO")
    private Ordered ordered;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_PRODUTO", referencedColumnName = "CODIGO")
    private Product product;

    @Column(name = "QUANTIDADE")
    private int quantity;

    @Override
    public int hashCode() {
        return Objects.hash(code, ordered, product, quantity);
    }

    public OrderedItem(Ordered ordered, Product product, int quantity) {
        this.ordered = ordered;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OrderedItem)) {
            return false;
        }
        OrderedItem other = (OrderedItem) obj;
        return Objects.equals(code, other.code)
                && Objects.equals(ordered, other.ordered)
                && Objects.equals(product, other.product)
                && Objects.equals(quantity, other.quantity);
    }
}
