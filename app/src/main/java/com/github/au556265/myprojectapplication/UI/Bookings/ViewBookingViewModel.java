package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.au556265.myprojectapplication.CallBacks.CallBackBooking;
import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.Repository.Booking.BookingRepository;

import java.util.ArrayList;
import java.util.List;

public class ViewBookingViewModel extends AndroidViewModel {
    private BookingRepository repository;
    private ArrayList<Booking> bookings;

    public ViewBookingViewModel(@NonNull Application app) {
        super(app);
        repository = BookingRepository.getInstance();
    }



    public LiveData<ArrayList<Booking>> getBookings() {
        return repository;
    }
}
