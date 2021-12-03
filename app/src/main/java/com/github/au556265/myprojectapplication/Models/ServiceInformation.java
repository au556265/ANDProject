package com.github.au556265.myprojectapplication.Models;

public class ServiceInformation {
    private String name;
    private int mIconId;


    public ServiceInformation(String info, int mIconId) {
        this.name = info;
        this.mIconId = mIconId;
    }

    public String getInfo() {
        return name;
    }

    public void setInfo(String info) {
        this.name = info;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int mIconId) {
        this.mIconId = mIconId;
    }
}
