package com.example.springapp.services;

import com.example.springapp.data.Product;
import com.example.springapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesProducts {

    private ProductRepository productRepository;

    public ServicesProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public Product getProductById(Long id){
        Product prod = productRepository.findById(id);
        return prod;
    }

    public void delProdictById(Long id) {
        productRepository.delProduct(id);
    }

}
