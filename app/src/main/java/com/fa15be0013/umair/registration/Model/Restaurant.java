package com.fa15be0013.umair.registration.Model;

/**
 * Created by user on 12/17/2017.
 */

public class Restaurant {
    String uid, id, fullName, email, password, restaurantName, restaurantAddress, city, image;

    public Restaurant(String restaurantName)
    {
        this.restaurantName = restaurantName;
    }

    public Restaurant(String uid, String restaurantName, String image)
    {
        this.uid = uid;
        this.restaurantName = restaurantName;
        this.image = image;
    }

    public Restaurant(String uid, String id, String fullName, String email, String password, String restaurantName, String restaurantAddress, String city) {
        this.uid = uid;
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.city = city;
    }

    public String getUid() {
        return uid;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }
}
