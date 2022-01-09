package com.avaliacao.ecommerce.service;

import com.avaliacao.ecommerce.controller.dto.ordered.ClientOrderedResponseDTO;
import com.avaliacao.ecommerce.controller.dto.ordered.OrderedItemRequestDTO;
import com.avaliacao.ecommerce.controller.dto.ordered.OrderedRequestDTO;
import com.avaliacao.ecommerce.controller.dto.ordered.OrderedResponseDTO;
import com.avaliacao.ecommerce.excecao.BusinessException;
import com.avaliacao.ecommerce.model.*;
import com.avaliacao.ecommerce.repository.OrderedItemRepository;
import com.avaliacao.ecommerce.repository.OrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderedService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderedRepository orderedRepository;

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    public ClientOrderedResponseDTO listOrderedByClient(int codeClient) {
        Client client = this.validateClientOrderedExists(codeClient);
        List<OrderedResponseDTO> orderedResponseDTOList = orderedRepository.findByClientCode(codeClient).stream()
                .map(ordered -> OrderedResponseDTO.converterToOrderedDTO(ordered, this.listOrderedItens(ordered.getCode())))
                .collect(Collectors.toList());
        return new ClientOrderedResponseDTO(client.getName(), orderedResponseDTOList);
    }

    public List<OrderedItem> listOrderedItens(int orderedCode) {
        return orderedItemRepository.findByOrderedByCode(orderedCode);
    }

    public Ordered findOrderedByCode(int code) {
        return orderedRepository.findByCode(code);
    }

    public Ordered save(OrderedRequestDTO orderedRequestDTO) {
        Client client = this.validateClientOrderedExists(orderedRequestDTO.getClientCode());
        Address address = this.validateAddressOrderedExists(orderedRequestDTO.getDeliveryCode());
        this.validateProductExistsAndQuantity(orderedRequestDTO.getItens());
        Ordered ordered = orderedRepository.save(orderedRequestDTO.converterOrderedModel(orderedRequestDTO.getDate(),
                                                client,
                                                address,
                                                orderedRequestDTO.getAmount()));

        orderedRequestDTO.getItens().stream().forEach(item -> {
            orderedItemRepository.save(item.converterOrderedItemModel(ordered, this.validateProductOrderedExists(item.getProductCode(), item.getCategoryCode()), item.getQuantity()));
        });
        return ordered;
    }

    public Ordered update(int code, OrderedRequestDTO orderedRequestDTO) {
        Ordered ordered = this.validateOrderedExists(code);
        //this.validateProductExistsAndQuantity(orderedRequestDTO.getItens());
        orderedRequestDTO.getItens().stream().forEach(item -> {
            orderedItemRepository.save(item.converterOrderedItemModel(ordered, this.validateProductOrderedExists(item.getProductCode(), item.getCategoryCode()), item.getQuantity()));
        });
        return ordered;
    }

    private Ordered validateOrderedExists(int code) {
        Ordered ordered = findOrderedByCode(code);
        if (ordered == null) {
            throw new BusinessException(String.format("Pedido de código %s não encontrada.", code));
        }
        return ordered;
    }

    private Client validateClientOrderedExists(int codeClient) {
        Client client = clientService.findByCode(codeClient);
        if (client != null) {
            throw new BusinessException(
                    String.format("O Cliente de código %s informado não existe no cadastro.", codeClient));
        }
        return client;
    }

    private Address validateAddressOrderedExists(int codeAddress) {
        Optional<Address> address = addressService.findByCode(codeAddress);
        if (address.isEmpty()) {
            throw new BusinessException(
                    String.format("O Endereço %s informado não existe no cadastro.", address));
        }
        return address.get();
    }

    private Product validateProductOrderedExists(int codeProduct, int codeCategory) {
        Optional<Product> product = productService.findByCode(codeProduct, codeCategory);
        if (product.isEmpty()) {
            throw new BusinessException(
                    String.format("O Produto %s informado não existe no cadastro.", product));
        }
        return product.get();
    }

    private void validateProductExistsAndQuantity(List<OrderedItemRequestDTO> itensDTO) {
        itensDTO.forEach(item -> {
            Product product = this.validateProductOrderedExists(item.getProductCode(), item.getCategoryCode());
            this.validateQuantityProduct(product, item.getQuantity());
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productService.save(item.getCategoryCode(), product);
        });
    }

    private void validateQuantityProduct(Product product, Integer quantity) {
        if(!(product.getQuantity() >= quantity)) {
            throw new BusinessException(String.format("A quantidade %s informada para o produto %s não está disponível em estoque",
                    quantity, product.getDescription()));
        }
    }
}
