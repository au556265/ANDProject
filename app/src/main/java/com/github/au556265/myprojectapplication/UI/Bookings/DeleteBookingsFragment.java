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
import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.R;

import java.util.ArrayList;


public class DeleteBookingsFragment extends Fragment {

    private DeleteBookingViewModel viewModel;
    RecyclerView recyclerView;
    DeleteBookingAdapter adapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_delete_bookings, container, false);
        viewModel = new ViewModelProvider(this).get(DeleteBookingViewModel.class);
        viewModel.init();

        recyclerView = view.findViewById(R.id.rv_delete_bookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        adapter = new DeleteBookingAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        getLiveDataBookings();
        return view;
    }

    public void deleteBooking(String id) {
        viewModel.deleteBooking(id);
    }

    private void getLiveDataBookings() {
        viewModel.getLiveDataBookings().observe(getViewLifecycleOwner(), new Observer<ArrayList<Booking>>() {
            @Override
            public void onChanged(ArrayList<Booking> bookings) {
                adapter.setBookingItems(bookings);
                //System.out.println(bookings.get(0));
            }
        });
    }
}