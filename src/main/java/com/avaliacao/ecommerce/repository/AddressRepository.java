package com.avaliacao.ecommerce.repository;

import com.avaliacao.ecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findByNome(String nome);
}
