package com.paulina.kuzmicka.discount.domain;

import java.math.BigDecimal;

public class Order {

    private Invoice invoice;
    private BigDecimal totalDiscount;


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
