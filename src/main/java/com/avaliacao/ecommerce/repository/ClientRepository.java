package com.avaliacao.ecommerce.repository;

import com.avaliacao.ecommerce.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
