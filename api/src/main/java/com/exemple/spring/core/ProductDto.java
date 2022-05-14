package com.exemple.spring.core;

public class ProductDto {
    private Long id;
    private String name;
    private Integer price;

    public ProductDto(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
