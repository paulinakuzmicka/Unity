package com.paulina.kuzmicka.discount.dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Invoice {
    private List<Item> items = new ArrayList<>();

    public Invoice(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Invoice{" + items +
                '}';
    }
}
