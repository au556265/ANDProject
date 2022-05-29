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

import com.github.au556265.myprojectapplication.Adapter.BookingAdapter2;
import com.github.au556265.myprojectapplication.CallBacks.CallBackBooking;
import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.R;

import java.util.ArrayList;


public class ViewBookingsFragment extends Fragment {
    private ViewBookingViewModel viewModel;
    RecyclerView recyclerView;
    BookingAdapter2 adapter;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recycle_view_bookings, container, false);

        viewModel = new ViewModelProvider(this).get(ViewBookingViewModel.class);
        viewModel.init();

        recyclerView = view.findViewById(R.id.rvbookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        adapter = new BookingAdapter2();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getBookings();
        return view;
    }

    private void getBookings() {
        viewModel.getBookings().observe(getViewLifecycleOwner(), new Observer<ArrayList<Booking>>() {
            @Override
            public void onChanged(ArrayList<Booking> bookings) {
                adapter.setBookingItems(bookings);
                System.out.println(bookings.get(0));
            }
        });

    }
}