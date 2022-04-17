package com.example.springapp.controllers;

import com.example.springapp.data.Product;
import com.example.springapp.services.ServicesProducts;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

   private ServicesProducts servicesProducts;

    public MainController(ServicesProducts servicesProducts) {
        this.servicesProducts = servicesProducts;
    }

    @GetMapping("/products")
    public List<Product> showProduct(){
        return servicesProducts.getProducts();
    }

    @GetMapping("/products/delete/{id}")
    public void delProducts(@PathVariable Long id){
        servicesProducts.delProdictById(id);
    }
    // /products/change_price

    @GetMapping("/products/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta){
        Product product = servicesProducts.getProductById(productId);
        product.changePrice(delta);
    }
}