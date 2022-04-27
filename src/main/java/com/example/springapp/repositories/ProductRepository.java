package com.example.springapp.repositories;

import com.example.springapp.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.price < :price")
    List<Product> lowPrice(Integer price);

    @Query("select p from Product p where p.price > :price")
    List<Product> hiPrice(Integer price);

    @Query("select p from Product p where p.price between :priceOne and :priceTwo")
    List<Product> findAllBetweenPrice(Integer priceOne, Integer priceTwo);
    }
