package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.au556265.myprojectapplication.Adapter.ViewBookingAdapter;
import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class ViewBookingsFragment extends Fragment {
    private ViewBookingViewModel viewModel;
    RecyclerView recyclerView;
    private Button btnFilter;
    private Button btnReset;
    ViewBookingAdapter adapter;
    private ArrayList<Booking> bookingArrayList = new ArrayList<>();
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recycle_view_bookings, container, false);

        viewModel = new ViewModelProvider(this).get(ViewBookingViewModel.class);
        viewModel.init();

        recyclerView = view.findViewById(R.id.rvbookings);
        btnFilter = view.findViewById(R.id.buttonFilter);
        btnReset = view.findViewById(R.id.buttonReset);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        adapter = new ViewBookingAdapter();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getBookings();

        btnFilter.setOnClickListener(view -> {
            showDateTime();
        });

        btnReset.setOnClickListener(view -> {
            getBookings();
            btnFilter.setText(getString(R.string.filter_by_date));
        });
        return view;
    }

    private void getBookings() {
        viewModel.getBookings().observe(getViewLifecycleOwner(), bookings -> {
            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            bookingArrayList.clear();

            for( int i = 0;i<bookings.size();i++){
                if(bookings.get(i).getEmail().equals(email)){
                    bookingArrayList.add(bookings.get(i));
                }
            }
            adapter.setBookingItems(bookingArrayList);
            System.out.println(bookings.get(0));
        });

    }

    public void showDateTime() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
            String selectedDate = simpleDateFormat.format(calendar.getTime());
            btnFilter.setText(selectedDate);
            ArrayList<Booking> filteredList = new ArrayList<>();

            for(int i = 0;i < bookingArrayList.size();i++){
                if(bookingArrayList.get(i).getBookingDate().equals(selectedDate)){
                    filteredList.add(bookingArrayList.get(i));
                }
            }

            adapter.setBookingItems(filteredList);


        };
        new DatePickerDialog(getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}