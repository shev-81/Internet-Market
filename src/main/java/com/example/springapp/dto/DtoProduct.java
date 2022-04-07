package com.example.springapp.dto;

public class DtoProduct {

    private Integer priceOne;
    private Integer priceTwo;

    public DtoProduct() {
    }

    public DtoProduct(Integer priceOne, Integer priceTwo) {
        this.priceOne = priceOne;
        this.priceTwo = priceTwo;
    }

    public Integer getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(Integer priceOne) {
        this.priceOne = priceOne;
    }

    public Integer getPriceTwo() {
        return priceTwo;
    }

    public void setPriceTwo(Integer priceTwo) {
        this.priceTwo = priceTwo;
    }
}
