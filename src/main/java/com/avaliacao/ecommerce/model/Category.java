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
@Table(name = "TB_ECOM_PRODUTO_CATEGORIA")
public class Category {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int code;

    @Column(name = "NOME")
    private String name;

    public Category(int code) {
        this.code = code;
    }

    public Category(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Category)) {
            return false;
        }
        Category other = (Category) obj;
        return Objects.equals(code, other.code)
                && Objects.equals(name, other.name);
    }
}
