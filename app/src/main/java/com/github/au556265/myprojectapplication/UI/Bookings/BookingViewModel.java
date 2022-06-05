package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.au556265.myprojectapplication.Repository.Booking.BookingRepository;
import com.github.au556265.myprojectapplication.Repository.User.UserRepository;


public class BookingViewModel extends BookingBaseViewModel{
    public BookingViewModel(@NonNull Application application) {
        super(application);
    }

    public void createBooking(String date, String time){
        bookingRepository.createBooking(date, time);
    }

}
