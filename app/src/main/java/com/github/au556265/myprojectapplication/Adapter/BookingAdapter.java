package com.github.au556265.myprojectapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.R;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{
    List<Booking> bookingList;
    //BookingAdapter.OnListItemClickListener listener;
    // BookingAdapter.OnListItemClickListener listener
    //List<Booking> bookings
    public BookingAdapter() {
        //this.bookingList = this.bookingList;
        //this.listener=listener;
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_viewbookings, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, int position) {
        holder.mDateTime.setText(bookingList.get(position).getBookingTime());
        holder.mEmail.setText(bookingList.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public void setBookingItems(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mDateTime;
        TextView mEmail;
        //TextView mTime;
        //String key;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(getAdapterPosition());
                }
            });*/
            mDateTime=itemView.findViewById(R.id.date_item);
            //mTime=itemView.findViewById(R.id.time_item);
            mEmail=itemView.findViewById(R.id.email_item);



        }
    }

    public interface  OnListItemClickListener{
        void onClick(int position);
    }
}
