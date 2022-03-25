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


//    // localhost:8189/app/first
//    @GetMapping("/first")
//    @ResponseBody
//    public String first(@RequestParam(required = false) int a, @RequestParam(name = "bb") int b){
//        return  "сумма A + B = " + (a+b);
//    }
//
//    // localhost:8189/app/product/{id}/info
//    @GetMapping("/showProdId/{id}/info")
//    @ResponseBody
//    public String showProdId(@PathVariable Long id){
//        return "Product" + id;
//    }
//
//    @GetMapping("/product/{id}")
//    public String showProd(Model model, @PathVariable Long id){
//        Product prod = productRepository.findById(id);
//        model.addAttribute("product", prod);
//        return "product_page";
//    }
//


}
