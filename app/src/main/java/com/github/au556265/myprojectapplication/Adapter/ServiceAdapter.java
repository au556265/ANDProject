package com.github.au556265.myprojectapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.au556265.myprojectapplication.Models.Service;
import com.github.au556265.myprojectapplication.R;
import com.github.au556265.myprojectapplication.UI.Services.RecycleViewServices;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder>{
    private final String email;
    private final RecycleViewServices recycleViewServices;

    public void setServiceInfo(List<Service> serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    List<Service> serviceInfo;
    OnListItemClickListener listener;

    public ServiceAdapter(String email, RecycleViewServices recycleViewServices) {
        this.recycleViewServices = recycleViewServices;
        serviceInfo = new ArrayList<>();
        this.email = email;
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

        if(email.equals("admin@hotmail.com"))
        {
            holder.mdeleteButton.setOnClickListener(v -> {
                recycleViewServices.deleteService(serviceInfo.get(position).getId());
            });
        }
    }

    @Override
    public int getItemCount() {
        return serviceInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView info;
        ImageView icon;
        TextView price;
        Button mdeleteButton;

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

            mdeleteButton = itemView.findViewById(R.id.delete_Services_button);

            if(!email.equals("admin@hotmail.com"))
                mdeleteButton.setVisibility(View.GONE);

        }
    }

    public interface  OnListItemClickListener{
        void onClick(int position);
    }
}
