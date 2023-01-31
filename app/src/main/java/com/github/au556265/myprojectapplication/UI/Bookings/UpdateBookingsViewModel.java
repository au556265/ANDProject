package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.github.au556265.myprojectapplication.Models.Booking;

import java.util.ArrayList;
import java.util.List;

public class UpdateBookingsViewModel extends BookingBaseViewModel{
    public UpdateBookingsViewModel(@NonNull Application application) {
        super(application);
    }
    public void updateBooking(String id, String date, String time){
        bookingRepository.updateBooking(id, date, time);
    }
    public LiveData<List<Booking>> getLiveDataBookings() {
        return bookingRepository.getMutableBookings();
    }
}
