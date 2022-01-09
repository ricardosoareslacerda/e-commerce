package com.avaliacao.ecommerce.controller.dto.ordered;

import com.avaliacao.ecommerce.controller.dto.address.AddressResponseDTO;
import com.avaliacao.ecommerce.controller.dto.client.ClientResponseDTO;
import com.avaliacao.ecommerce.model.Ordered;
import com.avaliacao.ecommerce.model.OrderedItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Pedido Retorno DTO")
public class OrderedResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private Integer code;

    @ApiModelProperty(value = "Data")
    private LocalDate date;

    @ApiModelProperty(value = "Cliente")
    private ClientResponseDTO client;

    @ApiModelProperty(value = "Endereço de Entrega")
    private AddressResponseDTO delivery;

    @ApiModelProperty(value = "Itens do pedido")
    private List<OrderedItemResponseDTO> itens;

    @ApiModelProperty(value = "Total do pedido")
    private double amount;

    public static OrderedResponseDTO converterToOrderedDTO(Ordered ordered, List<OrderedItem> orderedItemList) {

        OrderedResponseDTO orderedResponseDTO = new OrderedResponseDTO(ordered.getCode(),
                ordered.getDate(),
                ClientResponseDTO.converterToClientDTO(ordered.getClient()),
                AddressResponseDTO.converterToAddresResponseDTO(ordered.getDelivery()),
                ordered.getAmount());
        orderedResponseDTO.setItens(orderedItemList.stream().map(orderedItemModel -> OrderedItemResponseDTO.converterToOrderedItemResponseDTO(orderedResponseDTO, orderedItemModel)).collect(Collectors.toList()));
        return orderedResponseDTO;
    }

    public OrderedResponseDTO(Integer code, LocalDate date, ClientResponseDTO client, AddressResponseDTO delivery, double amount) {
        this.code = code;
        this.date = date;
        this.client = client;
        this.delivery = delivery;
        this.amount = amount;
    }
}
