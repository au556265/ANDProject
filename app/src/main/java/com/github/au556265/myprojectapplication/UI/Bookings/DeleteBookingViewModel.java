package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;


import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.Repository.Booking.BookingRepository;
import com.github.au556265.myprojectapplication.Repository.User.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class DeleteBookingViewModel extends BookingBaseViewModel {
    public DeleteBookingViewModel(@NonNull Application application) {
        super(application);
    }
    public void deleteBooking(String id){
        bookingRepository.DeleteBooking(id);
    }
    public LiveData<List<Booking>> getLiveDataBookings() {
        return bookingRepository.getMutableBookings();
    }

}
