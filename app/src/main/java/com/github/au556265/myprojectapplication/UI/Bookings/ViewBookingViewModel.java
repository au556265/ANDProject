package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.Repository.Booking.BookingRepository;
import com.github.au556265.myprojectapplication.Repository.User.UserRepository;

import java.util.ArrayList;

public class ViewBookingViewModel extends BookingBaseViewModel {
    public ViewBookingViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Booking>> getBookings() {
        return bookingRepository;
    }

}
