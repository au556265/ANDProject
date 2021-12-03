package com.github.au556265.myprojectapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.au556265.myprojectapplication.Models.ServiceInformation;
import com.github.au556265.myprojectapplication.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder>{
    List<ServiceInformation> serviceInfo;
    OnListItemClickListener listener;

    public ServiceAdapter(List<ServiceInformation> serviceInfo, OnListItemClickListener listener) {
        this.serviceInfo = serviceInfo;
        this.listener=listener;
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
        holder.info.setText(serviceInfo.get(position).getInfo());
        holder.icon.setImageResource(serviceInfo.get(position).getIconId());

    }

    @Override
    public int getItemCount() {
        return serviceInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView info;
        ImageView icon;

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

        }
    }

    public interface  OnListItemClickListener{
        void onClick(int position);
    }
}
