package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.au556265.myprojectapplication.Adapter.AvailableSlotsAdapter;
import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.Repository.Booking.BookingRepository;
import com.github.au556265.myprojectapplication.Repository.User.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingBaseViewModel extends AndroidViewModel {
    protected BookingRepository bookingRepository;
    protected final UserRepository userRepository;
    protected MutableLiveData<ArrayList<Booking>> listLiveData = new MutableLiveData<>();
    protected MutableLiveData<ArrayList<AvailableSlotsAdapter.Slot>> slotLiveData = new MutableLiveData<>();

    /**
     * Constructor which will be getting application as a parameter
     * and then initialize booking reporsitory.
     * @param application
     */
    public BookingBaseViewModel(@NonNull Application application) {
        super(application);
        bookingRepository = BookingRepository.getInstance();
        userRepository=UserRepository.getInstance(application);
    }

    /**
     * This method will initialize all dependencies required to perform all tasks
     * also setting observers which will update our live data on value updates.
     */
    public void init(){
        String userId = userRepository.getCurrentUser().getValue().getUid();
        bookingRepository.init(userId, new BookingRepository.BookingRepositoryListener() {
            @Override
            public void setValue(ArrayList<Booking> bookings) {
                if(bookings != null){
                    listLiveData.setValue(bookings);
                }
            }

            @Override
            public void slots(ArrayList<AvailableSlotsAdapter.Slot> slots) {
                if(slots != null){
                    slotLiveData.setValue(slots);
                }
            }
        });
    }
}
