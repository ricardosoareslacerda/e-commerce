package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.controller.dto.client.ClientRequestDTO;
import com.avaliacao.ecommerce.controller.dto.client.ClientResponseDTO;
import com.avaliacao.ecommerce.model.Client;
import com.avaliacao.ecommerce.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Listar", nickname = "findAll")
    @GetMapping
    public List<ClientResponseDTO> findAll() {
        return clientService.listAll().stream().map(cliente ->
                        ClientResponseDTO.converterToClientDTO(cliente))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por codigo", nickname = "findByCode")
    @GetMapping("/{codigo}")
    public ResponseEntity<ClientResponseDTO> findByCode(@PathVariable Integer codigo) {
        Optional<Client> clientModel = clientService.findByCode(codigo);
        return clientModel.isPresent()
                ? ResponseEntity.ok(ClientResponseDTO.converterToClientDTO(clientModel.get()))
                : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "salvar")
    @PostMapping
    public ResponseEntity<ClientResponseDTO> save(@Valid @RequestBody ClientRequestDTO clientRequest) {
        Client clientModel = clientService.save(clientRequest.converterClientModel());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientResponseDTO.converterToClientDTO(clientModel));
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Integer codigo, @RequestBody ClientRequestDTO clientRequest) {
        return ResponseEntity.ok(ClientResponseDTO.converterToClientDTO(clientService.update(codigo, clientRequest.converterClientModel(codigo))));
    }

    @ApiOperation(value = "Deletar", nickname = "deletar")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer codigo) {
        clientService.delete(codigo);
    }
}
