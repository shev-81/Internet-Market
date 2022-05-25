package com.exemple.spring.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель деталей в заказе")
public class OrderDetailsDto {

    @Schema(description = "Адресс в заказе", required = true, example = "г. Москва")
    private String address;

    @Schema(description = "Телефон в заказе", required = true, example = "8(903)495-12-23")
    private String phone;

    public OrderDetailsDto() {
    }

    public OrderDetailsDto(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
