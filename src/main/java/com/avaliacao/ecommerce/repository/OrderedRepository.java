package com.avaliacao.ecommerce.repository;

import com.avaliacao.ecommerce.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedRepository extends JpaRepository<Ordered, Integer> {

    List<Ordered> findByClientCode(Integer codeClient);

    Ordered findByCode(Integer code);
}
