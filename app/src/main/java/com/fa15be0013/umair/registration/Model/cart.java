package com.fa15be0013.umair.registration.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hamza Gaya on 12/26/2017.
 */

public class cart implements Serializable{

    private String Dish, Quantity, price;

    public cart(String dish, String quantity, String price) {
        Dish = dish;
        Quantity = quantity;
        this.price = price;
    }

    public String getDish() {
        return Dish;
    }

    public void setDish(String dish) {
        Dish = dish;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
