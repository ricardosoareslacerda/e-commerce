package com.avaliacao.ecommerce.repository;

import com.avaliacao.ecommerce.model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {

    @Query("select new com.avaliacao.ecommerce.model.OrderedItem("
            + " orderedItem.code, orderedItem.ordered, orderedItem.product, orderedItem.quantity)"
            + "  from OrderedItem orderedItem"
            + " where orderedItem.ordered.code = :codeOrdered")
    List<OrderedItem> findByOrderedByCode(Integer codeOrdered);

}
