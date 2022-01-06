package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.model.Ordered;
import com.avaliacao.ecommerce.service.OrderedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Pedido")
@RestController
@RequestMapping("/pedido")
public class OrderedController {

    @Autowired
    private OrderedService orderedService;

    @ApiOperation(value = "Listar")
    @GetMapping
    public List<Ordered> findAll() {
        return orderedService.listAllOrdereds();
    }

    @ApiOperation(value = "Encontrar por codigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Ordered>> findByCode(@PathVariable Integer codigo) {
        Optional<Ordered> orderedResponse = orderedService.findByCode(codigo);
        return orderedResponse.isPresent() ? ResponseEntity.ok(orderedResponse) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar")
    @PostMapping
    public ResponseEntity<Ordered> save(@RequestBody Ordered orderedRequest) {
        Ordered orderedResponse = orderedService.save(orderedRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderedResponse);
    }
}
