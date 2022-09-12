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

import com.github.au556265.myprojectapplication.Adapter.DeleteBookingAdapter;
import com.github.au556265.myprojectapplication.Adapter.UpdateBookingAdapter;
import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.R;

import java.util.ArrayList;

public class UpdateBookingsFragment extends Fragment {

    private UpdateBookingsViewModel viewModel;
    RecyclerView recyclerView;
    UpdateBookingAdapter adapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_bookings, container, false);
        viewModel = new ViewModelProvider(this).get(UpdateBookingsViewModel.class);
        viewModel.init();

        recyclerView = view.findViewById(R.id.rv_update_bookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        adapter = new UpdateBookingAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        getLiveDataBookings();

        return view;
    }
    public void updateBooking(String id, String date, String time) {
        viewModel.updateBooking(id, date, time);
    }

    private void getLiveDataBookings() {
        viewModel.getLiveDataBookings().observe(getViewLifecycleOwner(), new Observer<ArrayList<Booking>>() {
            @Override
            public void onChanged(ArrayList<Booking> bookings) {
                adapter.setBookingItems(bookings);
                System.out.println(bookings.get(0));
            }
        });
    }
}