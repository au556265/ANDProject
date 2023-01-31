package com.github.au556265.myprojectapplication.UI.Bookings;

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

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ViewBookingsFragment extends Fragment {
    private BookingViewModel viewModel;
    RecyclerView recyclerView;
    ViewBookingAdapter adapter;
    EditText date;
    Button filterButton;
    View view;

    ArrayList<String> adminAccount = new ArrayList<>();





    List<Booking> myBookings = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recycle_view_bookings, container, false);

        viewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        viewModel.init();

        recyclerView = view.findViewById(R.id.rvbookings);
        date = view.findViewById(R.id.filterDate);
        filterButton = view.findViewById(R.id.filterDateButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFilter();
            }
        });

        adapter = new ViewBookingAdapter();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adminAccount.add("admin@hotmail.com");
        getBookings();
        return view;
    }

    private void onClickFilter() {

        if(date.getText().toString().equals(""))
            adapter.setBookingItems(myBookings);
        else
        {
            ArrayList<Booking> filteredBookings = new ArrayList<>();

            for (int i = 0; i < myBookings.size(); i++) {
                if(Objects.equals(myBookings.get(i).getBookingDate(), date.getText().toString()))
                    filteredBookings.add(myBookings.get(i));
            }
            adapter.setBookingItems(filteredBookings);
        }
    }

    private void getBookings() {
        viewModel.getBookings().observe(getViewLifecycleOwner(), new Observer<List<Booking>>() {
            @Override
            public void onChanged(List<Booking> bookings) {
                boolean isAdmin = false;
                String email = viewModel.getEmail();
                for (int i = 0; i < adminAccount.size(); i++) {
                    if(email.equals(adminAccount.get(i))){
                        isAdmin = true;
                    }
                }
                if(!isAdmin){
                    myBookings = viewModel.getOnlyMyBookings();
                    adapter.setBookingItems(myBookings);
                }
                else {
                    myBookings = bookings;
                    adapter.setBookingItems(myBookings);
                }

            }
        });

    }
}