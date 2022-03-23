package com.example.springapp.repositories;

import com.example.springapp.data.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>(List.of(
                new Product(1L, "Apple"),
                new Product(2L, "Arbuz"),
                new Product(3L, "Tomato"),
                new Product(4L, "Banana"),
                new Product(5L, "Orange")
        ));
    }

    public Product findById(Long id){
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->new RuntimeException("No product wis that number"));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }
}
