package com.example.springapp.services;

import com.example.springapp.converters.OrderItemsConverter;
import com.example.springapp.dto.Cart;
import com.example.springapp.dto.OrderItemDto;
import com.example.springapp.entities.Orders;
import com.example.springapp.entities.OrderItem;
import com.example.springapp.entities.Product;
import com.example.springapp.entities.User;
import com.example.springapp.exceptions.ResourceNotFoundException;
import com.example.springapp.repositories.OrderItemsRepository;
import com.example.springapp.repositories.OrdersRepository;
import com.example.springapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ServicesProducts productsService;
    private final OrdersRepository ordersRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final OrderItemsConverter orderItemsConverter;
    private final UserRepository userRepository;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addProductByIdToCart(Long productId) {
        if (!getCurrentCart().addProduct(productId)) {
            Product product = productsService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найдет, id: " + productId));
            getCurrentCart().addProduct(product);
        }
    }

    @Transactional
    public void saveOrderFromCart(Principal principal){
        Orders orders = new Orders();
        List<OrderItem> orderItemList = new ArrayList<>();
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User  not found"));

        orders.setPhone("111-111-22-33");
        orders.setAddress("Lorem, ipsum dolor sit amet consectetur adipisicing elit. Velit atque quasi ipsum.");
        orders.setTotalPrice(0);
        orders.setOrderItems(orderItemList);
        orders.setUser(user);

        ordersRepository.save(orders);

        for(OrderItemDto o: cart.getItems()){
            OrderItem orderItem = orderItemsConverter.dtoToEntity(o);
            orderItem.setUsers(user);
            orderItem.setOrders(orders);
            orderItemsRepository.save(orderItem);
            orderItemList.add(orderItem);
        }
        // todo ПРОДОЛЖИТЬ ДЛЯ СОХРАНЕНИЯ В БД ОРДЕРА ДЛЯ ПОЛЬЗОВАТЕЛЯ
        user.getOrders().add(orders);
//        List<Orders> userOrdersList = new ArrayList<>();
//        userOrdersList.add(orders);
//        user.setOrders(userOrdersList);
        clear();
    }

    public void clear() {
        getCurrentCart().clear();
    }
}
