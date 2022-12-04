package com.github.au556265.myprojectapplication;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }
}
