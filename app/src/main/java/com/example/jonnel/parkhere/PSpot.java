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

    public String startDate;
    public String startTime;

    public String endTime;
    public String endDate;
    public Boolean availablity = true;
    public String owner;
    public String reserve;

    // creates empty parking spot
    public PSpot()
    {

    }

    public PSpot(double price, String address, String startDate, String endDate, String startTime, String endTime, Boolean availablity,String owner, String reserve)
    {
        this.price = price;
        this.address = address;
        this.startDate = startDate;
        this.endDate=endDate;
        this.startTime = startTime;
        this.endTime= endTime;
        this.availablity = availablity;
        this.owner = owner;
        this.reserve = reserve;
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
    public String getstartDate()
    {
        return startDate;
    }
    public String getendDate()
    {
        return endDate;
    }
    public String getStartTimeTime()
    {
        return startTime;
    }
    public String getEndTime()
    {
        return endTime;

    }



    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }
    public void setAddress(String address) { this.address = address; }
    public void setDate(String date) { this.date = date; }
    public void setTimee(String time) { this.time = time; }
    public void setTime(String endTime) { this.endTime = endTime; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public void setAvailablity(boolean availablity){this.availablity=availablity;}
    public void setOwner(String owner){this.owner=owner;}

    public String toString()
    {
        return "Parking Spot" + "\n"+ "$" + " " + getPrice() + "\n" + "Address: " +  getAddress() + "\n" + "Start Time: "+ startTime +"\n" + "End Time: " + endTime + "\n" + "Start Date: " + startDate + "\n" + "End Date: " + endDate +"\n"+ "Available: "+availablity;
    }
    public String ownerToString() {
        return "Parking Spot" + "\n"+"Owner:"+owner +"\n"+ "$" + " " + getPrice() + "\n" + "Address: " +  getAddress() + "\n" + "Start Time: "+ startTime +"\n" + "End Time: " + endTime + "\n" + "Start Date: " + startDate + "\n" + "End Date: " + endDate +"\n"+ "Available: "+availablity;
    }
    public String rentToString()
    {
        return "Parking Spot" + "\n"+"Owner:"+owner +"\n"+"Renter: " + reserve+ "\n"+ "$" + " " + getPrice() + "\n" + "Address: " +  getAddress() + "\n" + "Start Time: "+ startTime +"\n" + "End Time: " + endTime + "\n" + "Start Date: " + startDate + "\n" + "End Date: " + endDate +"\n"+ "Available: "+availablity;
    }

}

