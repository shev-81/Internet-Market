package com.example.springapp.converters;

import com.example.springapp.data.Product;
import com.example.springapp.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getPrice());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }
}
