package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "Data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "CODIGO_CLIENTE", referencedColumnName = "CODIGO")
    private Client client;

    @OneToOne
    @JoinColumn(name = "CODIGO_ENTREGA", referencedColumnName = "CODIGO")
    private Address delivery;

    @OneToMany
    @JoinColumn(name = "CODIGO_ITEM", referencedColumnName = "CODIGO")
    private List<OrderedItem> itens;

    @Column(name = "TOTAL")
    private double amount;

    @Override
    public int hashCode() {
        return Objects.hash(code, data, client, delivery, itens);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Ordered)) {
            return false;
        }
        Ordered other = (Ordered) obj;
        return Objects.equals(code, other.code)
                && Objects.equals(data, other.data)
                && Objects.equals(client, other.client)
                && Objects.equals(delivery, other.delivery)
                && Objects.equals(itens, other.itens);
    }
}
