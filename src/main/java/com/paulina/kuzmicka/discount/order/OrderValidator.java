package com.paulina.kuzmicka.discount.order;

import com.paulina.kuzmicka.discount.domain.Invoice;
import com.paulina.kuzmicka.discount.domain.Order;
import com.paulina.kuzmicka.discount.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderValidator {

    public boolean validateOrder(Order order) throws OrderException {
        return validateDiscountValue(order.getTotalDiscount()) &&
                validateItemsQuantity(order.getInvoice()) &&
                validateProductNames(order.getInvoice()) &&
                validateProductPrices(order.getInvoice()) &&
                validateTotalPriceAndDiscount(order);
    }

    private boolean validateDiscountValue(BigDecimal discount) throws OrderException {
        if (discount.compareTo(BigDecimal.ZERO) < 0 || discount.scale() > 2) {
            throw new OrderException(OrderException.ERR_DISCOUNT_VALUE);
        }
        return true;
    }

    private boolean validateItemsQuantity(Invoice invoice) throws OrderException {
        if (invoice.getItems().size() == 0 || invoice.getItems().size() > 5) {
            throw new OrderException(OrderException.ERR_INVOICE_ITEMS_QUANTITY);
        }
        return true;
    }

    private boolean validateProductNames(Invoice invoice) {
        invoice.getItems().stream()
                .map(item -> item.getProduct())
                .forEach(product -> {
                    try {
                        validateProductName(product);
                    } catch (OrderException e) {
                        System.out.println(e);
                    }
                });
        return true;
    }

    private boolean validateProductName(Product product) throws OrderException {
        String trimmedName = product.getName().trim();
        if (product.getName().length() < 3 || trimmedName.length() != product.getName().length()) {
            throw new OrderException(OrderException.ERR_PRODUCT_NAME);
        }
        return true;
    }

    private boolean validateProductPrices(Invoice invoice) {
        invoice.getItems().stream()
                .map(item -> item.getProduct())
                .forEach(product -> {
                    try {
                        validateProductPrice(product);
                    } catch (OrderException e) {
                        System.out.println(e);
                    }
                });
        return true;
    }

    private boolean validateProductPrice(Product product) throws OrderException {
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0 || product.getPrice().scale() > 2) {
            throw new OrderException(OrderException.ERR_PRODUCT_PRICE);
        }
        return true;
    }

    private boolean validateTotalPriceAndDiscount(Order order) throws OrderException {
        BigDecimal totalInvoicePrice = order.getInvoice().getItems().stream()
                .map(item -> item.getProduct().getPrice())
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));

        if (order.getTotalDiscount().compareTo(totalInvoicePrice) > 0) {
            throw new OrderException(OrderException.ERR_TOTAL_INVOICE_PRICE_AND_DISCOUNT);
        }
        return true;
    }
}
