package com.paulina.kuzmicka.discount.ui;

import com.paulina.kuzmicka.discount.domain.Invoice;
import com.paulina.kuzmicka.discount.domain.Item;
import com.paulina.kuzmicka.discount.domain.Order;
import com.paulina.kuzmicka.discount.domain.Product;
import com.paulina.kuzmicka.discount.order.OrderException;
import com.paulina.kuzmicka.discount.order.OrderProcessor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class UserInterface {

    private final OrderProcessor orderProcessor = new OrderProcessor();

    public void runProgram() {
        System.out.println(programInfo());
        boolean end = false;

        Invoice invoice = new Invoice();

        System.out.println("Please type product name and price: ");
        int counter = 0;
        do {
            System.out.println("Name: ");
            String name = readString();
            System.out.println("Price: ");
            BigDecimal price = readDouble();

            invoice.addItem(new Item(new Product(name, price)));
            counter++;
            if (counter < 5) {
                System.out.println("Do you want to add more products? (yes/other 3 signs)");
                if (!"yes".equalsIgnoreCase(readString())) {
                    end = true;
                }
            }
        } while (!end && counter < 5);

        System.out.println("Please type total discount: ");

        BigDecimal totalDiscount = readDouble();

        Order order = new Order();
        order.setInvoice(invoice);
        order.setTotalDiscount(totalDiscount);
        orderProcessor.setOrder(order);

        try {
            orderProcessor.processOrder();
        } catch (OrderException e) {
            System.out.println("There were some errors:");
            System.out.println(e);
        }
        System.out.println(printOrder());
    }

    private String programInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Simple program for dividing discount into products.");
        sb.append("\n");
        sb.append("You can define up to five products with prices and total discount for all of them.");
        sb.append("\n");
        sb.append("Program will divide discount into each product.");
        return sb.toString();
    }

    private String readString() {
        String result = "";
        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            result = scanner.nextLine();
            result = result.trim();
            if (result.length() >= 3) {
                end = true;
            } else {
                System.out.println("Please input minimum 3 characters.");
            }
        }
        return result;
    }

    private BigDecimal readDouble() {
        double readDouble;
        BigDecimal result = BigDecimal.ZERO;

        boolean end = false;
        while (!end) {
            Scanner scanner = new Scanner(System.in);
            try {
                readDouble = scanner.nextDouble();
                if (readDouble > 0) {
                    result = BigDecimal.valueOf(readDouble);
                    end = true;
                } else {
                    System.out.println("Please input positive value.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input positive value.");
                scanner.next();
            }
        }
        return result;
    }

    private String printOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("Summary:");
        sb.append("\n");
        sb.append("Total discount: ");
        sb.append(orderProcessor.getOrder().getTotalDiscount());
        sb.append("\n");
        sb.append("Products with divided discount:");
        sb.append("\n");
        sb.append(orderProcessor.getOrder().getInvoice().toString());

        return sb.toString();
    }
}
