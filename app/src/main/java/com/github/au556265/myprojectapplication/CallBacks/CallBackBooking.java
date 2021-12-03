package com.github.au556265.myprojectapplication.CallBacks;

import com.github.au556265.myprojectapplication.Models.Booking;

import java.util.List;

public interface CallBackBooking {
    void getAllBooking(List<Booking> bookings, List<String> keys);
}
