package com.github.au556265.myprojectapplication.UI.Services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.au556265.myprojectapplication.Adapter.ServiceAdapter;
import com.github.au556265.myprojectapplication.Adapter.ViewBookingAdapter;
import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.Models.Service;
import com.github.au556265.myprojectapplication.Models.ServiceInformation;
import com.github.au556265.myprojectapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class RecycleViewServices extends Fragment implements ServiceAdapter.OnListItemClickListener {

    RecyclerView recyclerView;
    AddServicesViewModel viewModel;
    ServiceAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        View view = inflater.inflate(R.layout.fragment_recycle_view_services, container, false);
        viewModel = new ViewModelProvider(this).get(AddServicesViewModel.class);
        viewModel.init();
        recyclerView = view.findViewById(R.id.rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();


        adapter = new ServiceAdapter(email, this);

        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
        getServices();
        adapter.notifyDataSetChanged();
        return view;
    }

    private void getServices() {
        viewModel.getServices().observe(getViewLifecycleOwner(), new Observer<List<Service>>() {
            @Override
            public void onChanged(List<Service> services) {
              /*  boolean isAdmin = false;
                String email = viewModel.getEmail();
                for (int i = 0; i < adminAccount.size(); i++) {
                    if(email.equals(adminAccount.get(i))){
                        isAdmin = true;
                    }
                }
                if(!isAdmin){
                    ArrayList<Booking> myBookings = viewModel.getOnlyMyBookings();
                    adapter.setBookingItems(myBookings);
                }
                else {
                }*/

                adapter.setServiceInfo(services);
                adapter.notifyDataSetChanged();
            }
        });

    }


    public void onClick(int position) {
        Toast.makeText(getContext(),"Position:",Toast.LENGTH_LONG).show();
    }

    public void deleteService(String id) {
        viewModel.deleteService(id);
        adapter.notifyDataSetChanged();
    }
}