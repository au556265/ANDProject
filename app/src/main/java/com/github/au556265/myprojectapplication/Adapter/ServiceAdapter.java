package com.github.au556265.myprojectapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.au556265.myprojectapplication.Models.Service;
import com.github.au556265.myprojectapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder>{
    public void setServiceInfo(List<Service> serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    List<Service> serviceInfo;
    OnListItemClickListener listener;

    public ServiceAdapter(List<Service> serviceInfo, OnListItemClickListener listener) {
        this.serviceInfo = serviceInfo;
        this.listener=listener;
    }

    public ServiceAdapter() {
        serviceInfo = new ArrayList<>();
    }

    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_services, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, int position) {
        holder.info.setText(serviceInfo.get(position).getName());
        holder.price.setText(serviceInfo.get(position).getPrice() + "");
        Glide.with(holder.itemView).load(serviceInfo.get(position).getUri()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return serviceInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView info;
        ImageView icon;
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(getAdapterPosition());
                }
            });
            info = itemView.findViewById(R.id.info);
            icon = itemView.findViewById(R.id.iv_icon);
            price = itemView.findViewById(R.id.price);

        }
    }

    public interface  OnListItemClickListener{
        void onClick(int position);
    }
}
