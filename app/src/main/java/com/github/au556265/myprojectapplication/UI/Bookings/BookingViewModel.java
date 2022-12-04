package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.au556265.myprojectapplication.Repository.Booking.BookingRepository;
import com.github.au556265.myprojectapplication.Repository.User.UserRepository;


public class BookingViewModel extends BookingBaseViewModel{
    /**
     * This constructor will provide object of bookingviewmodel
     * taking application as prameter
     * @param application
     */
    public BookingViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * This method will beresponsible for creating booking and calling
     * related methods in booking repository
     * @param date
     * @param time
     */
    public void createBooking(String date, String time){
        bookingRepository.createBooking(date, time);
    }

    /**
     * Slots will be fetched along with there status depending upon the date.
     * @param date
     */
    public void fetchSlots(String date){
        bookingRepository.getAllSlotsForDate(date);
    }

}
