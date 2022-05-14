package com.example.springapp.controllers;

import com.example.springapp.converters.ProductConverter;
import com.example.springapp.entities.Product;
import com.example.springapp.services.ProductService;
import com.example.springapp.validators.ProductValidator;
import com.exemple.spring.core.ProductDto;
import com.exemple.spring.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

   private ProductService productService;
   private ProductConverter productConverter;
   private ProductValidator productValidator;

   @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "name_category", required = false) String  nameCategory,
            @RequestParam(name = "name_part", required = false) String namePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, namePart, nameCategory, page).map(
                p -> productConverter.entityToDto(p)
        );
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @GetMapping("/all")
    public List<ProductDto> getAll(){
       return productService.findAll().stream().map(p -> productConverter.entityToDto(p)).collect(Collectors.toList());
   }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        productDto.setId(null);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delProducts(@PathVariable Long id){
        productService.delProdictById(id);
    }

    @PutMapping
    public ProductDto updateProducts(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return  productConverter.entityToDto(product);
    }
}
