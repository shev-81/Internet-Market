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
    public List<Product> findAllByLowPrice(Integer price){
        return productRepository.lowPrice(price);
    }

    @Transactional
    public List<Product> findAllByHiPrice(Integer price){
        return productRepository.hiPrice(price);
    }

    @Transactional
    public void delProdictById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public List<Product> findAllBetweenPrice(Integer priceOne, Integer priceTwo){
        return productRepository.findAllBetweenPrice(priceOne, priceTwo);
    }


}
