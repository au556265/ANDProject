package com.github.au556265.myprojectapplication.UI.Services;

import android.app.Application;

import androidx.annotation.NonNull;

public class AddServicesViewModel extends ServiceBaseViewModel {
    public AddServicesViewModel(@NonNull Application application) {
        super(application);
    }


    public void addService(String mName, double mPrice, byte[] stringImage) {
        servicesRepository.addServices(mName, mPrice, stringImage);
    }
    // TODO: Implement the ViewModel
}