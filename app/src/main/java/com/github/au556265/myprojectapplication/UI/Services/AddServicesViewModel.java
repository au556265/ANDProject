package com.github.au556265.myprojectapplication.UI.Services;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.Models.Service;

import java.util.ArrayList;
import java.util.List;

public class AddServicesViewModel extends ServiceBaseViewModel {
    public AddServicesViewModel(@NonNull Application application) {
        super(application);
    }


    public void addService(String mName, int mPrice, byte[] stringImage) {
        servicesRepository.addServices(mName, mPrice, stringImage);
    }

    public LiveData<List<Service>> getServices() {
        return servicesRepository.getSearchedServices();
    }

}