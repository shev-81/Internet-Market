package com.exemple.spring.models;

import com.exemple.spring.core.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String productName;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public CartItem(ProductDto productDto) {
        this.productId = productDto.getId();
        this.productName = productDto.getName();
        this.quantity = 1;
        this.pricePerProduct = productDto.getPrice();
        this.price = productDto.getPrice();
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }
}
