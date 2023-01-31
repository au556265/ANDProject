package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.au556265.myprojectapplication.Repository.Booking.BookingRepository;
import com.github.au556265.myprojectapplication.Repository.Booking.IBookingRepository;
import com.github.au556265.myprojectapplication.Repository.User.UserRepository;

public class BookingBaseViewModel extends AndroidViewModel {
    protected IBookingRepository bookingRepository;
    protected final UserRepository userRepository;

    public BookingBaseViewModel(@NonNull Application application) {
        super(application);
        bookingRepository = BookingRepository.getInstance();
        userRepository=UserRepository.getInstance(application);
    }

    public void init(){
        //String userId = userRepository.getCurrentUser().getValue().getUid();
        bookingRepository.init();
    }
}
