package com.paulina.kuzmicka.discount.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class Order {

    @Autowired
    private Invoice invoice;
    private BigDecimal totalDiscount;

    @Bean
    public Invoice getInvoice() {
        return invoice;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

}
