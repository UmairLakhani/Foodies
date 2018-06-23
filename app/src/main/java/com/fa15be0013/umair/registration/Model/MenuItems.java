package com.fa15be0013.umair.registration.Model;

import android.util.Pair;

import java.util.Arrays;

/**
 * Created by Hamza Gaya on 12/24/2017.
 */

public class MenuItems {

    String price, quantity, category;
    public MenuItems() {
    }

    public MenuItems(String price, String quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
