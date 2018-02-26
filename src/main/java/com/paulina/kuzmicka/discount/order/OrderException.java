package com.paulina.kuzmicka.discount.order;

public class OrderException extends Exception {

    public static String ERR_PRODUCT_NAME = "Product name is invalid";
    public static String ERR_PRODUCT_PRICE = "Product price is invalid";
    public static String ERR_DISCOUNT_VALUE = "Discount value is invalid";
    public static String ERR_INVOICE_ITEMS_QUANTITY = "Items quantity is invalid";
    public static String ERR_TOTAL_INVOICE_PRICE_AND_DISCOUNT = "Discount can't be grater than invoice price";

    public OrderException(String message) {
        super(message);
    }
}
