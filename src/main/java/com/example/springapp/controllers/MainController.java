package com.example.springapp.controllers;

import com.example.springapp.data.Product;
import com.example.springapp.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private ProductRepository productRepository;

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // localhost:8189/app/first
    @GetMapping("/first")
    @ResponseBody
    public String first(@RequestParam(required = false) int a, @RequestParam(name = "bb") int b){
        return  "сумма A + B = " + (a+b);
    }

    // localhost:8189/app/product/{id}/info
    @GetMapping("/showProdId/{id}/info")
    @ResponseBody
    public String showProdId(@PathVariable Long id){
        return "Product" + id;
    }

    @GetMapping("/product/{id}")
    public String showProd(Model model, @PathVariable Long id){
        Product prod = productRepository.findById(id);
        model.addAttribute("product", prod);
        return "product_page";
    }

    @GetMapping("/products")
    public String showProducts(Model model){
        model.addAttribute("products", productRepository.getProducts());
        return "products";
    }

}
