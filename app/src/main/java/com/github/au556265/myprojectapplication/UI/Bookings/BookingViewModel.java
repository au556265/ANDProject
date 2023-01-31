package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;


public class BookingViewModel extends BookingBaseViewModel{
    public BookingViewModel(@NonNull Application application) {
        super(application);
    }

    public void createBooking(String date, String time){
        bookingRepository.createBooking(date, time);
    }

    public boolean isBookingAvailable(String mDateTime, String mTimeSpinner) {
        return bookingRepository.isBookingAvaible(mDateTime, mTimeSpinner);
    }

    public boolean isBookingInFuture(String mDateTime, String mTimeSpinner) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return bookingRepository.isBookingInFuture(mDateTime, mTimeSpinner);
        }
        return false;
    }
}
