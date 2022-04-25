package com.example.springapp.controllers;


import com.example.springapp.converters.OrderConverter;
import com.example.springapp.dto.OrderDetailsDto;
import com.example.springapp.dto.OrderDto;
import com.example.springapp.entities.User;
import com.example.springapp.exceptions.ResourceNotFoundException;
import com.example.springapp.services.OrderService;
import com.example.springapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderDetailsDto orderDetailsDto) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createOrder(user, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findOrdersByUsername(principal.getName()).stream().map(o -> orderConverter.entityToDto(o)).collect(Collectors.toList());
    }
}



