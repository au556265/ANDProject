package com.github.au556265.myprojectapplication.Repository.Booking;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.github.au556265.myprojectapplication.Models.Booking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRepository implements IBookingRepository{
    private static BookingRepository instance;
    private static final String TAG = "repository";
    private DatabaseReference myRef;

    private String email;
    private final ArrayList<Booking> bookings = new ArrayList<>();
    private MutableLiveData<List<Booking>> mutableBookings = new MutableLiveData<>();


    private BookingRepository(){
    }

    public static synchronized BookingRepository getInstance() {
        if(instance == null)
            instance = new BookingRepository();
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void init() {
        myRef = FirebaseDatabase.getInstance("https://myprojectapplication-32774-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Bookings");
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DataChangeBookingsListener();
    }


    public void createBooking(String bookingDate, String bookingTime) {
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


    public void DataChangeBookingsListener(){
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
                mutableBookings.setValue(bookings);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }


    public ArrayList<Booking> getOnlyMyBookings() {
            ArrayList<Booking> myBookings = new ArrayList<>();
            String currentEmail = email;
            for (int i = 0; i < bookings.size(); i++) {
                if(bookings.get(i).getEmail().equals(currentEmail)){
                    myBookings.add(bookings.get(i));
                }
            }
            return myBookings;
    }

    public boolean isBookingAvaible(String date, String time) {
        for (int i = 0; i < bookings.size(); i++) {
            if(bookings.get(i).getBookingDate().equals(date)
                    && bookings.get(i).getBookingTime().equals(time)){
                return false;
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isBookingInFuture(String date, String time) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, pattern);

        if(localDate.isAfter(LocalDate.now()))
            return true;
        if(localDate.isEqual(LocalDate.now()))
        {
            String[] timeSplit = time.split(":");

            int hour = Integer.parseInt(timeSplit[0]);
            int minute = Integer.parseInt(timeSplit[1]);

            if(hour > LocalTime.now().getHour())
                return true;
            if(hour == LocalTime.now().getHour() && minute > LocalTime.now().getMinute())
                return true;
        }
        return false;
    }


    public MutableLiveData<List<Booking>> getMutableBookings() {
        return mutableBookings;
    }
}
