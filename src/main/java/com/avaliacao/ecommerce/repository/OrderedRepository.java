package com.avaliacao.ecommerce.repository;

import com.avaliacao.ecommerce.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedRepository extends JpaRepository<Ordered, Integer> {
}
