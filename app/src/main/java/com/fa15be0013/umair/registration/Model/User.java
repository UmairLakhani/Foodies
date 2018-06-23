package com.fa15be0013.umair.registration.Model;

/**
 * Created by user on 12/18/2017.
 */

public class User {
    String uid, id, fullName, email, password, gender, age, city;

    public User(String uid, String id, String fullName, String email, String password, String gender, String age, String city) {
        this.uid = uid;
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
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

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}
