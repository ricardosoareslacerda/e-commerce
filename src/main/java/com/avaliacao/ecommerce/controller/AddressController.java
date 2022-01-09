package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.controller.dto.address.AddressRequestDTO;
import com.avaliacao.ecommerce.controller.dto.address.AddressResponseDTO;
import com.avaliacao.ecommerce.model.Address;
import com.avaliacao.ecommerce.service.AddressService;
import com.avaliacao.ecommerce.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Endereco")
@RestController
@RequestMapping("/endereco")
public class AddressController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Listar", nickname = "findAll")
    @GetMapping
    public List<AddressResponseDTO> findAll() {
        return addressService.listAll().stream().map(addresModel ->
                        AddressResponseDTO.converterToAddresResponseDTO(addresModel))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por codigo", nickname = "findByCode")
    @GetMapping("/{codigo}")
    public ResponseEntity<AddressResponseDTO> findByCode(@PathVariable Integer codigo) {
        Optional<Address> addressModel = addressService.findByCode(codigo);
        return addressModel.isPresent()
                ? ResponseEntity.ok(AddressResponseDTO.converterToAddresResponseDTO(addressModel.get()))
                : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "salvar")
    @PutMapping("/{codigoCliente}")
    public ResponseEntity<AddressResponseDTO> save(@PathVariable Integer codigoCliente, @RequestBody AddressRequestDTO addressRequest) {

        Address addressModel = addressService.save(addressRequest.converterAddressModel(clientService.validateExists(codigoCliente)));
        return ResponseEntity.status(HttpStatus.CREATED).body(AddressResponseDTO.converterToAddresResponseDTO(addressModel));
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizar")
    @PutMapping("/cliente{codigoCliente}/{codigo}")
    public ResponseEntity<AddressResponseDTO> update(@PathVariable Integer codigoCliente, @PathVariable Integer codigo, @RequestBody AddressRequestDTO addressRequest) {
        return ResponseEntity.ok(AddressResponseDTO.converterToAddresResponseDTO(addressService.update(codigo, addressRequest.converterAddressModel(clientService.validateExists(codigoCliente)))));
    }

    @ApiOperation(value = "Deletar", nickname = "deletar")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer codigo) {
        addressService.delete(codigo);
    }
}
