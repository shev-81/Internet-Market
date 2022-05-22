package com.example.springapp.controllers;

import com.example.springapp.converters.ProductConverter;
import com.example.springapp.data.Product;
import com.example.springapp.dto.ProductDto;
import com.example.springapp.exceptions.ResourceNotFoundException;
import com.example.springapp.repositories.cart.ProductCart;
import com.example.springapp.services.ServicesProducts;
import com.example.springapp.validators.ProductValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

   private ServicesProducts servicesProducts;
   private ProductConverter productConverter;
   private ProductValidator productValidator;
   private ProductCart productCart;

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


    @GetMapping("/cart/{id}")
    public List<ProductDto> productInCart (@PathVariable Long id) {
        Product product = servicesProducts.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        productCart.add(productConverter.entityToDto(product));
        return productCart.getProductDtoList();
    }

    @GetMapping("/cart")
    public List<ProductDto> allProductCart () {
        return productCart.getProductDtoList();
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        productDto.setId(null);
        Product product = productConverter.dtoToEntity(productDto);
        product = servicesProducts.save(product);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/cart/{id}")
    public void delProductsFromCart(@PathVariable Long id){
        productCart.delProdictById(id);
    }

    @DeleteMapping("/{id}")
    public void delProducts(@PathVariable Long id){
        servicesProducts.delProdictById(id);
    }

    @PutMapping
    public ProductDto updateProducts(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = servicesProducts.save(product);
        return  productConverter.entityToDto(product);
    }

    @GetMapping("/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta){
        Product product = servicesProducts.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + productId));
        product.changePrice(delta);
        servicesProducts.save(product);
    }
}
