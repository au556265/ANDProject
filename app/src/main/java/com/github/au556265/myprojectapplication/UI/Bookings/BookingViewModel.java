package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.au556265.myprojectapplication.Repository.Booking.BookingRepository;
import com.github.au556265.myprojectapplication.Repository.User.UserRepository;


public class BookingViewModel extends AndroidViewModel {

    private BookingRepository bookingRepository;

    private final UserRepository userRepository;
    //private PropertyChangeSupport support;


    public BookingViewModel(@NonNull Application application) {
        super(application);
        bookingRepository = BookingRepository.getInstance();
        userRepository=UserRepository.getInstance(application);

    }
    public void init(){
        String userId = userRepository.getCurrentUser().getValue().getUid();
        bookingRepository.init(userId);
    }

    public void createBooking(String date, String time){
        bookingRepository.createBooking(date, time);
    }

    public void getAllBookings(){
        bookingRepository.registerBookingsListener();
    }



    /*
    @Override
    public void callBack(String message) {
        //support.firePropertyChange("BookingConfirmation", null, message);
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
    }

     */
}
