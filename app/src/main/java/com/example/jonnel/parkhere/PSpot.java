package com.example.jonnel.parkhere;

/**
 * Created by Jonnel on 10/30/2017.
 */

public class PSpot {

    public double price;
    public String status;
    public String address;
    public String date;
    public String time;
    public String endTime;
    public String endDate;

    // creates empty parking spot
    public PSpot()
    {
        price = 0;
        address = null;
        date = null;
        endDate = null;
        time = null;
        endTime = null;

    }

    // creates parking spot based on the parameters
    public PSpot(double price, String address, String date, String endDate, String time, String endTime)
    {
        this.price = price;
        this.address = address;
        this.date = date;
        this.endDate=endDate;
        this.time = time;
        this.endTime= endTime;
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

    public void setDat(String endDate) { this.endDate = endDate; }
    public void setTimee(String time) { this.time = time; }
    public void setTime(String endTime) { this.endTime = endTime; }

    public String toString(){
        return "Parking Spot: $" + " " + getPrice() + " " + getAddress() + " " + "Start Date: " + date + " " + "End Date: " + endDate;
    }
}
