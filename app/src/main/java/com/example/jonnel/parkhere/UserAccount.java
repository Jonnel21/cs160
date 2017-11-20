package com.example.jonnel.parkhere;

/**
 * Created by Daniel on 11/19/2017.
 */

public class UserAccount {
    public String firstName;
    public String lastName;
    public String email;
    public String address;
    public String city;
    public String state;
    public String zip;


    public UserAccount() {
        firstName = null;
        lastName = null;
        email = null;
        address = null;
        city = null;
        state = null;
        zip = null;
    }

    public UserAccount(String firstName, String lastName, String email, String address, String city, String state, String zip) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
    }
}
