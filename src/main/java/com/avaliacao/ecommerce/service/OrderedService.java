package com.avaliacao.ecommerce.service;

import com.avaliacao.ecommerce.model.Ordered;
import com.avaliacao.ecommerce.repository.OrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderedService {

    @Autowired
    private OrderedRepository orderedRepository;

    public List<Ordered> listAllOrdereds() {
        return orderedRepository.findAll();
    }

    public Optional<Ordered> findByCode(int code) {
        return orderedRepository.findById(code);
    }

    public Ordered save(Ordered ordered) {
        return orderedRepository.save(ordered);
    }
}
