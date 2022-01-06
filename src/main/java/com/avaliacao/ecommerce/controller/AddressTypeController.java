package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.model.AddressType;
import com.avaliacao.ecommerce.service.AddressTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Tipo Endereco")
@RestController
@RequestMapping("/endereco/tipo")
public class AddressTypeController {

    @Autowired
    private AddressTypeService addressTypeService;

    @ApiOperation(value = "Listar")
    @GetMapping
    public List<AddressType> findAll() {
        return addressTypeService.listAllAddresss();
    }

    @ApiOperation(value = "Encontrar por codigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<AddressType>> findByCode(@PathVariable Integer codigo) {
        Optional<AddressType> addressResponse = addressTypeService.findByCode(codigo);
        return addressResponse.isPresent() ? ResponseEntity.ok(addressResponse) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar")
    @PostMapping
    public ResponseEntity<AddressType> save(@RequestBody AddressType addressTypeRequest) {
        AddressType addressTypeResponse = addressTypeService.save(addressTypeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressTypeResponse);
    }

    @ApiOperation(value = "Atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<AddressType> update(@PathVariable Integer codigo, @RequestBody AddressType addressTypeRequest) {
        return ResponseEntity.ok(addressTypeService.update(codigo, addressTypeRequest));
    }
}
