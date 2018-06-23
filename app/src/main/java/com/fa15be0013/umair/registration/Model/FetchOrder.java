package com.fa15be0013.umair.registration.Model;

/**
 * Created by Hamza Gaya on 12/31/2017.
 */

public class FetchOrder {
    String address, phoneNumber, Date, orderID;

    public FetchOrder() {
    }

    public FetchOrder(String address, String phoneNumber, String date, String orderID) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        Date = date;
        this.orderID = orderID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserAddress() {
        return address;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public String getDate() {
        return Date;
    }

    public String getOrderID() {
        return orderID;
    }
}
