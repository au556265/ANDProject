package com.github.au556265.myprojectapplication.Repository.Booking;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.github.au556265.myprojectapplication.Models.Booking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class BookingLiveData extends LiveData<Booking> {

    private final ValueEventListener listener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Booking booking = snapshot.getValue(Booking.class);
            setValue(booking);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };

    DatabaseReference databaseReference;

    public BookingLiveData(DatabaseReference ref) {
        databaseReference = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }

}
