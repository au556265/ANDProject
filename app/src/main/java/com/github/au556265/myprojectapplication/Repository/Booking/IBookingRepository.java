package com.github.au556265.myprojectapplication.Repository.Booking;

import androidx.lifecycle.MutableLiveData;

import com.github.au556265.myprojectapplication.Models.Booking;

import java.util.ArrayList;
import java.util.List;

public interface IBookingRepository {
    String getEmail();
    void init();
    void createBooking(String bookingDate, String bookingTime);
    void DeleteBooking(String id);
    void updateBooking(String id, String date, String time);
    ArrayList<Booking> getOnlyMyBookings();
    boolean isBookingAvaible(String date, String time);
    boolean isBookingInFuture(String date, String time);
    MutableLiveData<List<Booking>> getMutableBookings();
}
