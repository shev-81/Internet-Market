package com.example.springapp.dto;

import com.example.springapp.dto.OrderItemDto;
import lombok.Data;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> items;
    private Integer totalPrice;
    private String address;
    private String phone;
}
