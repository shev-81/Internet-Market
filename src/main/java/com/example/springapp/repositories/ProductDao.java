package com.example.springapp.repositories;

import com.example.springapp.data.Product;

import java.util.List;

public interface ProductDao {
    Product findById(Long id);
    List<Product> findAll();
    void save(Product product);
    void delProduct(Long id);
}
