package com.github.au556265.myprojectapplication.Models;

public class Booking {
    private String bookingTime;
    private String bookingDate;
    private String email;
    private String id;
    //private String phonenumber;

    public Booking(){}

    public Booking(String bookingDate, String bookingTime, String email){
        this.bookingDate=bookingDate;
        this.bookingTime=bookingTime;
        this.email = email;
    }
    /*
    public Booking(String bookingDate, String bookingTime, String email, String phonenumber){
        this.bookingDate=bookingDate;
        this.bookingTime=bookingTime;
        this.email = email;
        this.phonenumber=phonenumber;
    }
    */

/*
    public Booking(String bookingTime){
        //this.bookingDate=bookingDate;
        this.bookingTime=bookingTime;

    }

 */
    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String dateTime) {
        this.bookingTime = dateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
