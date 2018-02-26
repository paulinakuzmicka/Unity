package com.paulina.kuzmicka.discount.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class Item {
    private Product product;
    private BigDecimal discount;

    public Item(Product product) {
        this.product = product;
        discount = BigDecimal.ZERO;
    }

    @Bean
    public Product getProduct() {
        return product;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Item{" + product +
                ", discount=" + discount +
                '}';
    }
}
