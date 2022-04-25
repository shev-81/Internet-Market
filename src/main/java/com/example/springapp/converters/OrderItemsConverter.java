package com.example.springapp.converters;

import com.example.springapp.dto.OrderItemDto;
import com.example.springapp.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemsConverter {

    public OrderItem dtoToEntity(OrderItemDto orderItemDto) {
        throw new UnsupportedOperationException();
    }

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getName(), orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}
