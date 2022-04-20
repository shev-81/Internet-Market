package com.example.springapp.repositories;

import com.example.springapp.data.Product;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {

    private ProductDaoImpl productDao;

    public ProductRepository(ProductDaoImpl productDao) {
        this.productDao = productDao;
    }

    public Product findById(Long id){
        return productDao.findById(id);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(productDao.findAll());
    }

    public void delProduct(Long id){
        productDao.delProduct(id);
    }

    public void save(Product product){
        productDao.save(product);
    }
}
