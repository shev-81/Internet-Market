package com.example.springapp.controllers;

import com.example.springapp.converters.OrderConverter;
import com.exemple.spring.core.OrderDetailsDto;
import com.example.springapp.services.OrderService;
import com.exemple.spring.core.OrderDto;
import com.exemple.spring.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Методы работы с заказами")
public class OrdersController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @Operation(
            summary = "Запрос на создание нового заказа",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content()
                    )
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader @Parameter(description = "Заголовок с именем пользователя", required = true) String username,
                            @RequestBody @Parameter(description = "DTO с деталями заказа", required = true) OrderDetailsDto orderDetailsDto) {
        orderService.createOrder(username, orderDetailsDto);
    }


    @GetMapping
    @Operation(
            summary = "Запрос на получение списка заказов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = OrderDto.class))
                            )
                    )
            }
    )
    public List<OrderDto> getCurrentUserOrders(@RequestHeader @Parameter(description = "Заголовок с именем пользователя", required = true) String username) {
        return orderService.findOrdersByUsername(username).stream().map(o -> orderConverter.entityToDto(o)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Запрос на получение заказа по ID",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = OrderDto.class))
                    )
            }
    )
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderConverter.entityToDto(orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("ORDER 404")));
    }
}



