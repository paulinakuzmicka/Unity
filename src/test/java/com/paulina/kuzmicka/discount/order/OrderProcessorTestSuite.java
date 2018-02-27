package com.paulina.kuzmicka.discount.order;

import com.paulina.kuzmicka.discount.domain.Invoice;
import com.paulina.kuzmicka.discount.domain.Item;
import com.paulina.kuzmicka.discount.domain.Order;
import com.paulina.kuzmicka.discount.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderProcessorTestSuite {

    @InjectMocks
    private OrderProcessor orderProcessor;

    @Mock
    private OrderValidator validator;

    @Test
    public void testSetProporcionalDiscountForProducts() throws OrderException {
        //Given
        Order order = new Order();
        Invoice invoice = new Invoice();
        Product product1 = new Product("Test1", BigDecimal.TEN);
        Product product2 = new Product("Test2", BigDecimal.TEN);
        Item item1 = new Item(product1);
        Item item2 = new Item(product2);
        invoice.addItem(item1);
        invoice.addItem(item2);

        order.setInvoice(invoice);
        order.setTotalDiscount(BigDecimal.valueOf(2));

        orderProcessor.setOrder(order);

        when(validator.validateOrder(order)).thenReturn(true);

        //When
        orderProcessor.processOrder();

        //Then
        BigDecimal partialDiscount = BigDecimal.valueOf(1.00).setScale(2, BigDecimal.ROUND_DOWN);
        BigDecimal item1Discount = orderProcessor.getOrder().getInvoice().getItems().get(0).getDiscount();
        BigDecimal item2Discount = orderProcessor.getOrder().getInvoice().getItems().get(1).getDiscount();
        assertTrue(partialDiscount.equals(item1Discount));
        assertTrue(partialDiscount.equals(item2Discount));
    }

    @Test
    public void testSetDifferentDiscountForProductsWithRestForTheLast() throws OrderException {
        //Given
        Order order = new Order();
        Invoice invoice = new Invoice();
        Product product1 = new Product("Test1", BigDecimal.TEN);
        Product product2 = new Product("Test2", BigDecimal.TEN);
        Product product3 = new Product("Test3", BigDecimal.valueOf(30));
        Item item1 = new Item(product1);
        Item item2 = new Item(product2);
        Item item3 = new Item(product3);
        invoice.addItem(item1);
        invoice.addItem(item2);
        invoice.addItem(item3);

        order.setInvoice(invoice);
        order.setTotalDiscount(BigDecimal.valueOf(5.53));

        orderProcessor.setOrder(order);

        when(validator.validateOrder(order)).thenReturn(true);

        //When
        orderProcessor.processOrder();

        //Then
        BigDecimal lastItemDiscount = BigDecimal.valueOf(3.33);
        BigDecimal item1Discount = orderProcessor.getOrder().getInvoice().getItems().get(0).getDiscount();
        BigDecimal item2Discount = orderProcessor.getOrder().getInvoice().getItems().get(1).getDiscount();
        BigDecimal item3Discount = orderProcessor.getOrder().getInvoice().getItems().get(2).getDiscount();
        assertTrue(item1Discount.equals(item2Discount));
        assertTrue(lastItemDiscount.equals(item3Discount));
    }
}