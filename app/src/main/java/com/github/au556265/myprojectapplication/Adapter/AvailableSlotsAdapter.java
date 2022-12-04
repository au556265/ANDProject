package com.github.au556265.myprojectapplication.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.au556265.myprojectapplication.Models.ServiceInformation;
import com.github.au556265.myprojectapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AvailableSlotsAdapter  extends RecyclerView.Adapter<AvailableSlotsAdapter.ViewHolder>{
    List<Slot> slots;
    AvailableSlotsAdapter.OnListItemClickListener listener;

    //This field is to maintain selection of items.
    public int selectedIndex = -1;

    /**
     * Constructor to initialize adapter with default values and callback
     * @param slots
     * @param listener
     */
    public AvailableSlotsAdapter(List<Slot> slots, AvailableSlotsAdapter.OnListItemClickListener listener) {
        this.slots = slots;
        this.listener=listener;
    }

    @NonNull
    @Override
    public AvailableSlotsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.slot_item, parent, false);
        return new AvailableSlotsAdapter.ViewHolder(view);

    }

    /**
     * Default method which is used to set data on recycler item on a specific position
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull AvailableSlotsAdapter.ViewHolder holder, int position) {
        holder.info.setText(slots.get(position).getTime());

        //if slot is not available then it will set background color red
        if(!slots.get(position).isAvailable){
            holder.backGround.setCardBackgroundColor(Color.RED);
        }

        //if slot is selected then it will set background color yellow
        if(position == selectedIndex){
            holder.backGround.setCardBackgroundColor(Color.YELLOW);
        }else{
            if(slots.get(position).isAvailable){
                holder.backGround.setCardBackgroundColor(Color.GREEN);
            }
        }
    }

    @Override
    public int getItemCount() {
        return slots.size();
    }

    public Slot getItemAt(int position) {
        return slots.get(position);
    }

    /**
     * Will be used to set values and notify updater to update item
     * @param slots
     */
    public void setItems(ArrayList<Slot> slots) {
        this.slots =slots;
        notifyDataSetChanged();
    }

    /**
     * this method will be used to update selection
     * @param i
     */
    public void setSelectedIndex(int i) {
        selectedIndex = i;
        notifyDataSetChanged();
    }

    /**
     * view holder is responsible for initializing view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView info;
        CardView backGround;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Listener that listens to when an item is being clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(slots.get(getAdapterPosition()).isAvailable){
                        listener.onSlotClick(getAdapterPosition());
                    }
                }
            });
            info = itemView.findViewById(R.id.info);
            backGround = itemView.findViewById(R.id.card);

        }
    }

    public interface  OnListItemClickListener{
        void onSlotClick(int position);
    }

    public static class Slot{
        public Slot(String time, boolean isAvailable) {
            this.time = time;
            this.isAvailable = isAvailable;
        }

        String time;
        boolean isAvailable;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }
    }
}
