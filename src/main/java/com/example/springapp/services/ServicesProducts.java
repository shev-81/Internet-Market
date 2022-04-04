package com.example.springapp.services;

import com.example.springapp.data.Product;
import com.example.springapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicesProducts {

    private ProductRepository productRepository;

    public ServicesProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product getProductById(Long id){
        Product prod = productRepository.findById(id).get();
        return prod;
    }

    @Transactional
    public void delProdictById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void save(Product product){
        productRepository.save(product);
    }

}
