package com.example.jonnel.parkhere;

/**
 * Created by Jonnel on 10/30/2017.
 */

public class PSpot {

    private double price;
    private String status;
    private String address;
    private String date;
    private String time;

    // creates empty parking spot
    public PSpot()
    {
        price = 0;
        status = null;
        address = null;
        date = null;
        time = null;

    }

    // creates parking spot based on the parameters
    public PSpot(double price, String status, String address, String date, String time)
    {
        this.price = price;
        this.status = status;
        this.address = address;
        this.date = date;
        this.time = time;
    }


    public double getPrice()
    {
        return price;
    }

    public String getStatus()
    {
        return status;
    }

    public String getAddress()
    {
        return address;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }
    public void setAddress(String address) { this.address = address; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }

}
