package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.model.Address;
import com.avaliacao.ecommerce.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Endereco")
@RestController
@RequestMapping("/endereco")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Listar")
    @GetMapping
    public List<Address> findAll() {
        return addressService.listAllAddresss();
    }

    @ApiOperation(value = "Encontrar por codigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Address>> findByCode(@PathVariable Integer codigo) {
        Optional<Address> addressResponse = addressService.findByCode(codigo);
        return addressResponse.isPresent() ? ResponseEntity.ok(addressResponse) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar")
    @PostMapping
    public ResponseEntity<Address> save(@RequestBody Address addressRequest) {
        Address addressResponse = addressService.save(addressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressResponse);
    }

    @ApiOperation(value = "Atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<Address> update(@PathVariable Integer codigo, @RequestBody Address addressRequest) {
        return ResponseEntity.ok(addressService.update(codigo, addressRequest));
    }
}
