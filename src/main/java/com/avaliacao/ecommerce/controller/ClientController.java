package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.model.Client;
import com.avaliacao.ecommerce.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Listar")
    @GetMapping
    public List<Client> findAll() {
        return clientService.listAllClients();
    }

    @ApiOperation(value = "Encontrar por codigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Client>> findByCode(@PathVariable Integer codigo) {
        Optional<Client> clientResponse = clientService.findByCode(codigo);
        return clientResponse.isPresent() ? ResponseEntity.ok(clientResponse) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar")
    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client clientRequest) {
        Client clientResponse = clientService.save(clientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
    }

    @ApiOperation(value = "Atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<Client> update(@PathVariable Integer codigo, @RequestBody Client clientRequest) {
        return ResponseEntity.ok(clientService.update(codigo, clientRequest));
    }
}
