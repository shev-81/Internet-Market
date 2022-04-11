package com.example.springapp.controllers;

import com.example.springapp.converters.ProductConverter;
import com.example.springapp.data.Product;
import com.example.springapp.dto.ProductDto;
import com.example.springapp.services.ServicesProducts;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

   private ServicesProducts servicesProducts;
   private ProductConverter productConverter;

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
    public ProductDto saveProduct(@RequestBody ProductDto productDto){
        productDto.setId(null);
        Product p = productConverter.dtoToEntity(productDto);
        return productConverter.entityToDto(servicesProducts.save(p));
    }

    @DeleteMapping("/{id}")
    public void delProducts(@PathVariable Long id){
        servicesProducts.delProdictById(id);
    }

    @PutMapping
    public ProductDto updateProducts(@RequestBody ProductDto productDto) {
        Product product = productConverter.dtoToEntity(productDto);
        return  productConverter.entityToDto(servicesProducts.save(product));
    }

    @GetMapping("/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta){
        Product product = servicesProducts.getProductById(productId);
        product.changePrice(delta);
        servicesProducts.save(product);
    }
}
