package com.github.au556265.myprojectapplication.Models;

import android.net.Uri;

import java.net.URI;

public class Service {
    private String name;
    private int price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    private Uri uri;


    private byte[] imageString;
    public Service(String name, int price, byte[] imageString) {
        this.name = name;
        this.price = price;
        this.imageString = imageString;
    }

    public Service(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Service() {

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
