package com.github.au556265.myprojectapplication.Repository.Booking;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.au556265.myprojectapplication.CallBacks.CallBackBooking;
import com.github.au556265.myprojectapplication.Models.Booking;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRepository extends LiveData<ArrayList<Booking>> {
    private static BookingRepository instance;
    private static final String TAG = "repository";
    private DatabaseReference myRef;

    private BookingLiveData currentBooking;
    private final ArrayList<Booking> bookings = new ArrayList<>();

    private BookingRepository(){
    }

    public static synchronized BookingRepository getInstance() {
        if(instance == null)
            instance = new BookingRepository();
        return instance;
    }

    public void init(String userId) {
        myRef = FirebaseDatabase.getInstance("https://myprojectapplication-32774-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Bookings");
        //currentBooking = new BookingLiveData(myRef);
       registerBookingsListener();


    }

    public void createBooking(String bookingDate, String bookingTime) {
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        myRef.push().setValue(new Booking(bookingDate,bookingTime,email));
    }

    public void DeleteBooking(String id){
        myRef.child(id).removeValue();
    }

    public void updateBooking(String id, String date, String time){
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("bookingDate",date);
        hashMap.put("bookingTime",time);
        myRef.child(id).updateChildren(hashMap);
    }

    public void registerBookingsListener(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                bookings.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    Log.d(TAG, "onDataChange: " + postSnapshot);
                    keys.add(postSnapshot.getKey());
                    Booking booking = postSnapshot.getValue(Booking.class);
                    booking.setId(postSnapshot.getKey());
                    bookings.add(booking);

                }
                setValue(bookings);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }



}
