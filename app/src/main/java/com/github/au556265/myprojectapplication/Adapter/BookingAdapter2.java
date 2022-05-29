package com.github.au556265.myprojectapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.R;
import com.github.au556265.myprojectapplication.UI.Bookings.ViewBookingsFragment;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter2 extends RecyclerView.Adapter<BookingAdapter2.ViewHolder>{
    private List<Booking> bookingList = new ArrayList<>();
    private ViewBookingsFragment viewBookingsFragment;
    public BookingAdapter2() {
    }

    @NonNull
    @Override
    public BookingAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_viewbookings, parent, false);
        return new BookingAdapter2.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter2.ViewHolder holder, int position) {
        holder.mDateTime.setText(bookingList.get(position).getBookingDate());
        holder.mEmail.setText(bookingList.get(position).getEmail());
        holder.mTime.setText(bookingList.get(position).getBookingTime());
    }

    @Override
    public int getItemCount() {
        if(bookingList==null){
            return 0;
        }else
        return bookingList.size();
    }



    public void setBookingItems(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mDateTime;
        TextView mEmail;
        TextView mTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mDateTime=itemView.findViewById(R.id.date_item);
            mTime=itemView.findViewById(R.id.time_item);
            mEmail=itemView.findViewById(R.id.email_item);


        }
    }

}

