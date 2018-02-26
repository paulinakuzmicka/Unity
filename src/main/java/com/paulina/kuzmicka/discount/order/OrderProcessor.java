package com.paulina.kuzmicka.discount.order;

import com.paulina.kuzmicka.discount.dao.Item;
import com.paulina.kuzmicka.discount.dao.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderProcessor {
    @Autowired
    private Order order;

    @Autowired
    private OrderValidator validator;

    public void processOrder() throws OrderException {

        if (validator.validateOrder(order)) {
            setPartialDiscountForAllProducts();
        }
    }

    private void setPartialDiscountForAllProducts() {
        setProporcionalDiscountForAllProducts();
        setRemainedDiscountForLastProduct();
    }

    private void setProporcionalDiscountForAllProducts() {
        order.getInvoice().getItems().stream()
                .forEach(item -> item.setDiscount(calculatedDiscount(item)));
    }

    private BigDecimal calculatedDiscount(Item item) {
        return item.getProduct().getPrice()
                .multiply(order.getTotalDiscount())
                .divide(totalInvoicePrice(), 2, BigDecimal.ROUND_DOWN);
    }

    private BigDecimal totalInvoicePrice() {
        return order.getInvoice().getItems().stream()
                .map(item -> item.getProduct().getPrice())
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));
    }

    private void setRemainedDiscountForLastProduct() {
        BigDecimal remainedDiscount = remainedDiscountToSet();
        if (remainedDiscount.compareTo(BigDecimal.ZERO) > 0) {
            Item lastItem = order.getInvoice().getItems().get(order.getInvoice().getItems().size() - 1);
            lastItem.setDiscount(lastItem.getDiscount().add(remainedDiscount));
        }
    }

    private BigDecimal remainedDiscountToSet() {
        BigDecimal settedDiscount = order.getInvoice().getItems().stream()
                .map(item -> item.getDiscount())
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));

        return order.getTotalDiscount().subtract(settedDiscount);
    }
}
