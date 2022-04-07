package com.example.springapp.controllers;

import com.example.springapp.data.Product;
import com.example.springapp.services.ServicesProducts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private ServicesProducts servicesProducts;

    public MainController(ServicesProducts servicesProducts) {
        this.servicesProducts = servicesProducts;
    }

    @GetMapping("/products/betweenprice")
    public List<Product> getPriceProductsBetween(@RequestParam Integer priceOne, @RequestParam Integer priceTwo){
        System.out.println(priceOne+ "" + priceTwo);
        return servicesProducts.findAllBetweenPrice(priceOne, priceTwo);
    }
}
