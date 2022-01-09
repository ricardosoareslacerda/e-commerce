package com.avaliacao.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TB_ECOM_CLIENTE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int code;

    @Column(name = "NOME")
    private String name;

    @Column(name = "CPF")
    private Long cpf;

    @Column(name = "CELULAR")
    private Long cell;

    @Column(name = "E_MAIL")
    private String email;

    public Client(String name, Long cpf, Long cell, String email) {
        this.name = name;
        this.cpf = cpf;
        this.cell = cell;
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, cpf, cell, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Client)) {
            return false;
        }
        Client other = (Client) obj;
        return Objects.equals(code, other.code)
                && Objects.equals(name, other.name)
                && Objects.equals(cpf, other.cpf)
                && Objects.equals(cell, other.cell)
                && Objects.equals(email, other.email);
    }
}
