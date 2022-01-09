package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ECOM_PRODUTO")
public class Product {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int code;

    @Column(name = "NOME")
    private String name;

    @Column(name = "DESCRICAO")
    private String description;

    @Column(name = "QUANTIDADE")
    private int quantity;

    @Column(name = "VALOR")
    private double value;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_CATEGORIA", referencedColumnName = "CODIGO")
    private Category category;

    public Product(int code) {
        this.code = code;
    }

    public Product(String name, String description, int quantity, double value, Category category) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.value = value;
        this.category = category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, code, description, quantity, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
        return Objects.equals(category, other.category)
                && Objects.equals(code, other.code)
                && Objects.equals(description, other.description)
                && Objects.equals(quantity, other.quantity)
                && Objects.equals(value, other.value);
    }
}
