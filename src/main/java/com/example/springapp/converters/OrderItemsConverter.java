package com.example.springapp.converters;

import com.example.springapp.dto.OrderItemDto;
import com.example.springapp.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemsConverter {

    public OrderItem dtoToEntity(OrderItemDto o) {

        OrderItem oItem = new OrderItem();
        oItem.setProductId(o.getProductId());
        oItem.setQuantity(o.getQuantity());
        oItem.setPricePerProduct(o.getPricePerProduct());
        oItem.setPrice(o.getPricePerProduct() * o.getQuantity());

        return oItem;
    }
}
