package com.github.au556265.myprojectapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.R;
import com.github.au556265.myprojectapplication.UI.Bookings.DeleteBookingsFragment;

import java.util.ArrayList;
import java.util.List;

public class DeleteBookingAdapter extends RecyclerView.Adapter<DeleteBookingAdapter.ViewHolder>{
    private List<Booking> bookingList = new ArrayList<>();
   private DeleteBookingsFragment deleteBookingsFragment;

    public DeleteBookingAdapter(DeleteBookingsFragment deleteBookingsFragment) {
    this.deleteBookingsFragment=deleteBookingsFragment;
    }

    @NonNull
    @Override
    public DeleteBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_delete_booking_item, parent, false);
        return new DeleteBookingAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DeleteBookingAdapter.ViewHolder holder, int position) {
        holder.mDateTime.setText(bookingList.get(position).getBookingDate());
        holder.mEmail.setText(bookingList.get(position).getEmail());
        holder.mTime.setText(bookingList.get(position).getBookingTime());
        holder.mdeleteButton.setOnClickListener(v -> {  deleteBookingsFragment.deleteBooking(bookingList.get(position).getId() );

        });
    }

    @Override
    public int getItemCount() {
        if(bookingList==null){
            return 0;
        }else
            return bookingList.size();
    }
    public void setBookingItems(List<Booking> bookingList) {
        this.bookingList = bookingList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mDateTime;
        TextView mEmail;
        TextView mTime;
        Button mdeleteButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mdeleteButton = itemView.findViewById(R.id.delete_bookings_button);
            mDateTime=itemView.findViewById(R.id.date_item_delete);
            mTime=itemView.findViewById(R.id.time_item_delete);
            mEmail=itemView.findViewById(R.id.email_item_delete);

        }
    }

}
