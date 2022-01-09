package com.avaliacao.ecommerce.controller;

import com.avaliacao.ecommerce.controller.dto.ordered.ClientOrderedResponseDTO;
import com.avaliacao.ecommerce.controller.dto.ordered.OrderedRequestDTO;
import com.avaliacao.ecommerce.controller.dto.ordered.OrderedResponseDTO;
import com.avaliacao.ecommerce.model.Ordered;
import com.avaliacao.ecommerce.service.ClientService;
import com.avaliacao.ecommerce.service.OrderedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Pedido")
@RestController
@RequestMapping("/pedido")
public class OrderedController {

    @Autowired
    private OrderedService orderedService;

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Listar pedidos por cliente", nickname = "listarPedidosPorCliente")
    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClientOrderedResponseDTO> listOrderedByClient(@PathVariable Integer codigoCliente){
        return ResponseEntity.ok(orderedService.listOrderedByClient(codigoCliente));
    }

    @ApiOperation(value = "Listar pedidos por código", nickname = "listarPedidosPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<OrderedResponseDTO> listOrderedByCode(@PathVariable Integer codigo) {
        return ResponseEntity.ok(OrderedResponseDTO.converterToOrderedDTO(orderedService.findOrderedByCode(codigo),
                orderedService.listOrderedItens(codigo)));
    }

    @ApiOperation(value = "Registrar pedido", nickname = "registrarPedido")
    @PostMapping
    public ResponseEntity<OrderedResponseDTO> registerOrdered(@Valid @RequestBody OrderedRequestDTO orderedRequestDTO){
        Ordered ordered = orderedService.save(orderedRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderedResponseDTO.converterToOrderedDTO(ordered, orderedService.listOrderedItens(ordered.getCode())));
    }

    @ApiOperation(value = "Atualizar pedido", nickname = "atualizarPedido")
    @PutMapping("/{codigo}")
    public ResponseEntity<OrderedResponseDTO> updateOrdered(@PathVariable Integer codigo, @Valid @RequestBody OrderedRequestDTO orderedRequestDTO){
        Ordered ordered = orderedService.update(codigo, orderedRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderedResponseDTO.converterToOrderedDTO(ordered, orderedService.listOrderedItens(ordered.getCode())));
    }
}
