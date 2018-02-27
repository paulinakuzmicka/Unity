package com.paulina.kuzmicka.discount.domain;

import java.util.ArrayList;
import java.util.List;


public class Invoice {
    private List<Item> items = new ArrayList<>();

    public Invoice() {
    }

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
