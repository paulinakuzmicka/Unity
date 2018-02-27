package com.paulina.kuzmicka.discount.order;

import com.paulina.kuzmicka.discount.domain.Invoice;
import com.paulina.kuzmicka.discount.domain.Item;
import com.paulina.kuzmicka.discount.domain.Order;
import com.paulina.kuzmicka.discount.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderValidatorTestSuite {
    @InjectMocks
    private OrderValidator validator;

    @Test(expected = OrderException.class)
    public void testValidateDiscountValue() throws OrderException {
        //Given
        Order order = new Order();
        order.setTotalDiscount(BigDecimal.valueOf(-10));

        //When
        validator.validateOrder(order);
    }

    @Test(expected = OrderException.class)
    public void testValidateItemsQuantityZero() throws OrderException {
        //Given
        Order order = new Order();
        order.setTotalDiscount(BigDecimal.valueOf(10));
        order.setInvoice(new Invoice());

        //When
        validator.validateOrder(order);
    }

    @Test(expected = OrderException.class)
    public void testValidateItemsQuantitySix() throws OrderException {
        //Given
        Order order = new Order();
        order.setTotalDiscount(BigDecimal.valueOf(10));
        Invoice invoice = new Invoice();
        invoice.addItem(new Item(new Product("Test", BigDecimal.ONE)));
        invoice.addItem(new Item(new Product("Test2", BigDecimal.ONE)));
        invoice.addItem(new Item(new Product("Test3", BigDecimal.ONE)));
        invoice.addItem(new Item(new Product("Test4", BigDecimal.ONE)));
        invoice.addItem(new Item(new Product("Test5", BigDecimal.ONE)));
        invoice.addItem(new Item(new Product("Test6", BigDecimal.ONE)));
        order.setInvoice(invoice);

        //When
        validator.validateOrder(order);
    }

    @Test(expected = OrderException.class)
    public void testValidateProductsNames() throws OrderException {
        //Given
        Order order = new Order();
        order.setTotalDiscount(BigDecimal.valueOf(10));
        Invoice invoice = new Invoice();
        invoice.addItem(new Item(new Product("Test", BigDecimal.ONE)));
        invoice.addItem(new Item(new Product("  2  ", BigDecimal.ONE)));
        order.setInvoice(invoice);

        //When
        validator.validateOrder(order);
    }

    @Test(expected = OrderException.class)
    public void testValidateProductsPricesNegative() throws OrderException {
        //Given
        Order order = new Order();
        order.setTotalDiscount(BigDecimal.valueOf(10));
        Invoice invoice = new Invoice();
        invoice.addItem(new Item(new Product("Test", BigDecimal.ONE)));
        invoice.addItem(new Item(new Product("Test2", BigDecimal.valueOf(-10))));
        order.setInvoice(invoice);

        //When
        validator.validateOrder(order);
    }

    @Test(expected = OrderException.class)
    public void testValidateProductsPricesScale() throws OrderException {
        //Given
        Order order = new Order();
        order.setTotalDiscount(BigDecimal.valueOf(10));
        Invoice invoice = new Invoice();
        invoice.addItem(new Item(new Product("Test", BigDecimal.ONE)));
        invoice.addItem(new Item(new Product("Test2", BigDecimal.valueOf(0.999))));
        order.setInvoice(invoice);

        //When
        validator.validateOrder(order);
    }

    @Test(expected = OrderException.class)
    public void testValidateTotalPriceAndDiscount() throws OrderException {
        //Given
        Order order = new Order();
        order.setTotalDiscount(BigDecimal.valueOf(100));
        Invoice invoice = new Invoice();
        invoice.addItem(new Item(new Product("Test", BigDecimal.ONE)));
        invoice.addItem(new Item(new Product("Test2", BigDecimal.valueOf(15))));
        order.setInvoice(invoice);

        //When
        validator.validateOrder(order);
    }
}