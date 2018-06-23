package com.fa15be0013.umair.registration.Model;

import java.util.ArrayList;

/**
 * Created by Hamza Gaya on 12/25/2017.
 */

public class MenuList {
    public String category;
    public ArrayList<MenuItems> item = new ArrayList<MenuItems>();


    public MenuList(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
