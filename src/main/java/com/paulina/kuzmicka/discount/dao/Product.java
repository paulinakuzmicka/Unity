package com.paulina.kuzmicka.discount.dao;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Product {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
