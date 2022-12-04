package com.github.au556265.myprojectapplication.Repository.Booking;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.au556265.myprojectapplication.Adapter.AvailableSlotsAdapter;
import com.github.au556265.myprojectapplication.CallBacks.CallBackBooking;
import com.github.au556265.myprojectapplication.Models.Booking;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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

public class BookingRepository  {
    private static BookingRepository instance;
    private static final String TAG = "repository";
    private DatabaseReference myRef;
    private BookingRepositoryListener bookingsListener;

    //private BookingLiveData currentBooking;
    private final ArrayList<Booking> bookings = new ArrayList<>();
    private final ArrayList<AvailableSlotsAdapter.Slot> slots = new ArrayList<>();
    private ValueEventListener valueChangeListener;

    private BookingRepository(){
    }

    public static synchronized BookingRepository getInstance() {
        if(instance == null)
            instance = new BookingRepository();
        return instance;
    }

    /**
     *
     * @param userId
     * @param bookingRepositoryListener
     * This method will initialize booking repository and provide
     * it all data and objects required for performing actions related to this repository
     */
    public void init(String userId,BookingRepositoryListener bookingRepositoryListener) {
        myRef = FirebaseDatabase.getInstance("https://myprojectapplication-32774-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Bookings");
        //currentBooking = new BookingLiveData(myRef);
        this.bookingsListener = bookingRepositoryListener;
        setUpBookingListener();
        registerBookingsListener();

    }

    /**
     * This method will be used to initialize valueChanged
     * listener which will actually observe any changes in booking
     * repository in that path
     */
    private void setUpBookingListener() {
        valueChangeListener = new ValueEventListener() {
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
                bookingsListener.setValue(bookings);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };
    }

    /**
     * @param bookingDate
     * @param bookingTime
     * Create booking method will be adding a new item on firebase database
     * against that user,date and time.
     */
    public void createBooking(String bookingDate, String bookingTime) {
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        myRef.push().setValue(new Booking(bookingDate,bookingTime,email)).
                addOnCompleteListener(task -> getAllSlotsForDate(bookingDate));
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
        myRef.addValueEventListener(valueChangeListener);
    }

    public void getAllSlotsForDate(String date){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                slots.clear();

                slots.add(new AvailableSlotsAdapter.Slot("11:00",true));
                slots.add(new AvailableSlotsAdapter.Slot("11:30",true));
                slots.add(new AvailableSlotsAdapter.Slot("12:00",true));
                slots.add(new AvailableSlotsAdapter.Slot("12:30",true));
                slots.add(new AvailableSlotsAdapter.Slot("13:00",true));
                slots.add(new AvailableSlotsAdapter.Slot("13:30",true));
                slots.add(new AvailableSlotsAdapter.Slot("14:00",true));

                slots.add(new AvailableSlotsAdapter.Slot("14:30",true));
                slots.add(new AvailableSlotsAdapter.Slot("15:00",true));
                slots.add(new AvailableSlotsAdapter.Slot("15:30",true));
                slots.add(new AvailableSlotsAdapter.Slot("16:00",true));
                slots.add(new AvailableSlotsAdapter.Slot("16:30",true));
                slots.add(new AvailableSlotsAdapter.Slot("17:00",true));


                List<String> keys = new ArrayList<>();

                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    Log.d(TAG, "onDataChange: " + postSnapshot);
                    keys.add(postSnapshot.getKey());
                    Booking booking = postSnapshot.getValue(Booking.class);
                    booking.setId(postSnapshot.getKey());


                    if(booking.getBookingDate().equalsIgnoreCase(date)){

                        for (AvailableSlotsAdapter.Slot slot : slots) {
                            if(slot.getTime().equalsIgnoreCase(booking.getBookingTime())){
                                slot.setAvailable(false);
                            }
                        }

                    }

                }
                bookingsListener.slots(slots);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public interface BookingRepositoryListener{

        void setValue(ArrayList<Booking> bookings);
        void slots(ArrayList<AvailableSlotsAdapter.Slot> slots);
    }



}
