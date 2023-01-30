package com.github.au556265.myprojectapplication.Models;

public class Service {
    private String name;
    private double price;

    private byte[] imageString;
    public Service(String name, double price, byte[] imageString) {
        this.name = name;
        this.price = price;
        this.imageString = imageString;
    }

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public byte[] getImageString() {
        return imageString;
    }

    public void setImageString(byte[] imageString) {
        this.imageString = imageString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
