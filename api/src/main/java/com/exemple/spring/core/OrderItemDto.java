package com.exemple.spring.core;

public class OrderItemDto {
    private Long productId;
    private String productName;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(Long productId, String productName, int quantity, int pricePerProduct, int price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }

    public OrderItemDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(int pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
