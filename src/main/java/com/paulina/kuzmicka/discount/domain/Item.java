package com.paulina.kuzmicka.discount.domain;

import java.math.BigDecimal;


public class Item {
    private final Product product;
    private BigDecimal discount;

    public Item(Product product) {
        this.product = product;
        discount = BigDecimal.ZERO;
    }

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
        return "\nItem{" + product +
                ", discount=" + discount +
                '}';
    }
}
