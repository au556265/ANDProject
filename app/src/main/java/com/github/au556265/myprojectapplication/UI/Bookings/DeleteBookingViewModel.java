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

public class DeleteBookingViewModel extends BookingBaseViewModel {
    public DeleteBookingViewModel(@NonNull Application application) {
        super(application);
    }
    //extends AndroidViewModel
    /*private BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public DeleteBookingViewModel(@NonNull Application app) {
        super(app);
        bookingRepository = BookingRepository.getInstance();
        userRepository = UserRepository.getInstance(app);
    }
    public void init(){
        String userId = userRepository.getCurrentUser().getValue().getUid();
        bookingRepository.init(userId);
    }
   */
    public void deleteBooking(String id){
        bookingRepository.DeleteBooking(id);
    }
    public LiveData<ArrayList<Booking>> getLiveDataBookings() {
        return bookingRepository;
    }

}
