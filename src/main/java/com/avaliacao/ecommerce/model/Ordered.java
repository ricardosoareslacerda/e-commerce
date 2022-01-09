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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int code;

    @Column(name = "Data")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_CLIENTE", referencedColumnName = "CODIGO")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_ENTREGA", referencedColumnName = "CODIGO")
    private Address delivery;

    @Column(name = "TOTAL")
    private double amount;

    public Ordered(LocalDate data, Client client, Address delivery, double amount) {
        this.date = data;
        this.client = client;
        this.delivery = delivery;
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, date, client, delivery);
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
                && Objects.equals(date, other.date)
                && Objects.equals(client, other.client)
                && Objects.equals(delivery, other.delivery);
    }
}
