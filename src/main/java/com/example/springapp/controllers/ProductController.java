package com.example.springapp.controllers;

import com.example.springapp.converters.ProductConverter;
import com.example.springapp.entities.Product;
import com.example.springapp.dto.ProductDto;
import com.example.springapp.exceptions.ResourceNotFoundException;
import com.example.springapp.services.ServicesProducts;
import com.example.springapp.validators.ProductValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

   private ServicesProducts servicesProducts;
   private ProductConverter productConverter;
   private ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "name_part", required = false) String namePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return servicesProducts.find(minPrice, maxPrice, namePart, page)
                .map(s -> new ProductDto(s.getId(),s.getName(), s.getPrice())
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ProductDto saveProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        productDto.setId(null);
        Product product = productConverter.dtoToEntity(productDto);
        product = servicesProducts.save(product);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delProducts(@PathVariable Long id){
        servicesProducts.delProdictById(id);
    }

    @PutMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ProductDto updateProducts(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = servicesProducts.save(product);
        return  productConverter.entityToDto(product);
    }
}
