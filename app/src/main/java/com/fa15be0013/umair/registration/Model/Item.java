package com.fa15be0013.umair.registration.Model;

/**
 * Created by user on 12/20/2017.
 */

public class Item {
    public String quantity, price;

    public Item(String quantity, String price) {
        this.quantity = quantity;
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }
}
