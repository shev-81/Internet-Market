package com.example.springapp.repositories;

import com.example.springapp.data.Product;

import java.util.List;

public interface ProductDao {
    Product findById(Long id);
    Product findByName(String name);
    List<Product> findAll();
    void save(Product product);
    void updateNameById(Long id, String newName);
    void testCaching();
    void delProduct(Long id);
}
