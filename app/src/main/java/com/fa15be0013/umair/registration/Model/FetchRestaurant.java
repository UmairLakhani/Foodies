package com.fa15be0013.umair.registration.Model;

/**
 * Created by Hamza Gaya on 12/21/2017.
 */

public class FetchRestaurant{
   private String restaurantName,image,uid;


    public FetchRestaurant() {
    }

    public FetchRestaurant(String restaurantName, String image, String uid) {
        this.restaurantName = restaurantName;
        this.image = image;
        this.uid = uid;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getimage() {
        return image;
    }

    public String getUid() {
        return uid;
    }

    public void setrestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public void setRestId(String uid) {
        this.uid = uid;
    }
}
