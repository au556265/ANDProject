package com.github.au556265.myprojectapplication.UI.Services;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.au556265.myprojectapplication.Repository.Booking.BookingRepository;
import com.github.au556265.myprojectapplication.Repository.Services.ServicesRepository;
import com.github.au556265.myprojectapplication.Repository.User.UserRepository;

public class ServiceBaseViewModel extends AndroidViewModel {

    protected ServicesRepository servicesRepository;

    public ServiceBaseViewModel(@NonNull Application application) {
        super(application);
        servicesRepository = ServicesRepository.getInstance();
    }

    public void init() {
        servicesRepository.init();
    }

}
