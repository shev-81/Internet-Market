package com.example.springapp.services;


import com.example.springapp.entities.Order;
import com.example.springapp.entities.OrderItem;
import com.example.springapp.integrations.CartServiceIntegration;
import com.example.springapp.repositories.OrdersRepository;
import com.example.spring.auth.UserDto;
import com.exemple.spring.cart.CartDto;
import com.exemple.spring.core.OrderDetailsDto;
import com.exemple.spring.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.springapp.enums.StatusCode.PAID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productsService;
    private final RestTemplate authServiceTemplate;

    @Value("${integrations.auth-service.url}")
    private String authServiceUrl;

    @Transactional
    public void createOrder(String userName, OrderDetailsDto orderDetailsDto) {
        CartDto currentCart = cartServiceIntegration.getUserCart(userName);
        Order order = new Order();
        order.setUsername(userName);
        order.setFio(authServiceTemplate.getForObject(authServiceUrl+ "/api/v1/user/" + userName, UserDto.class).getFio());
        order.setPostalCode(orderDetailsDto.getPostalcode());
        order.setAdminAreaTwoTown(orderDetailsDto.getAdminAreaTwoTown());
        order.setAddressLineOneStreet(orderDetailsDto.getAddressLineOneStreet());
        order.setAddressLineTwoApartmentNumber(orderDetailsDto.getAddressLineTwoApartmentNumber());
        order.setEmail(orderDetailsDto.getEmail());
        order.setPhone(orderDetailsDto.getPhone());
        order.setTotalPrice(currentCart.getTotalPrice());
        List<OrderItem> items = currentCart.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setQuantity(o.getQuantity());
                    item.setPricePerProduct(o.getPricePerProduct());
                    item.setPrice(o.getPrice());
                    item.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    return item;
                }).collect(Collectors.toList());
        order.setItems(items);
        ordersRepository.save(order);
        cartServiceIntegration.clearUserCart(userName);
    }

    public List<Order> findOrdersByUsername(String username) {
        return ordersRepository.findAllByUsername(username);
    }

    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Transactional
    public void saveOrderById(Long id){
       Order order = findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
       order.setStatus(PAID.name());
    }
}


