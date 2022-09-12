package com.github.au556265.myprojectapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.R;
import com.github.au556265.myprojectapplication.UI.Bookings.UpdateBookingsFragment;

import java.util.ArrayList;
import java.util.List;

public class UpdateBookingAdapter extends RecyclerView.Adapter<UpdateBookingAdapter.ViewHolder>{
    private List<Booking> bookingList = new ArrayList<>();
    private UpdateBookingsFragment updateBookingsFragment;
    private Spinner time_spinner;

    public UpdateBookingAdapter(UpdateBookingsFragment updateBookingsFragment) {
        this.updateBookingsFragment=updateBookingsFragment;
    }

    @NonNull
    @Override
    public  UpdateBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_update_booking_item, parent, false);
        time_spinner = view.findViewById(R.id.time_array);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(parent.getContext(),
                R.array.time_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(adapter);
        return new  UpdateBookingAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  UpdateBookingAdapter.ViewHolder holder, int position) {
        holder.mDateTime.setText(bookingList.get(position).getBookingDate());
        holder.mEmail.setText(bookingList.get(position).getEmail());
        holder.mTime.setText(bookingList.get(position).getBookingTime());
        holder.mUpdateButton.setOnClickListener(v -> {  updateBookingsFragment.updateBooking(bookingList.get(position).
                getId(),holder.mUpdateDate.getText().toString(),holder.mUpdateTime.getSelectedItem().toString() );
        });

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
        Button mUpdateButton;
        EditText mUpdateDate;
        Spinner  mUpdateTime;

   public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mUpdateButton = itemView.findViewById(R.id.update_button_bookintItem);
            mDateTime=itemView.findViewById(R.id.date_item_update);
            mTime=itemView.findViewById(R.id.time_item_update);
            mEmail=itemView.findViewById(R.id.email_item_update);
            mUpdateDate=itemView.findViewById(R.id.date_update);
            mUpdateTime = itemView.findViewById(R.id.time_array);



        }
    }

}
