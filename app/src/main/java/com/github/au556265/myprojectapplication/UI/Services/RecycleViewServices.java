package com.github.au556265.myprojectapplication.UI.Services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.au556265.myprojectapplication.Adapter.ServiceAdapter;
import com.github.au556265.myprojectapplication.Models.ServiceInformation;
import com.github.au556265.myprojectapplication.R;

import java.util.ArrayList;
import java.util.List;


public class RecycleViewServices extends Fragment implements ServiceAdapter.OnListItemClickListener {

    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycle_view_services, container, false);
        recyclerView = view.findViewById(R.id.rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        List<ServiceInformation> informationList = new ArrayList<>();

        informationList.add(new ServiceInformation("Men's haircut: 170 kr", R.drawable.mens_haircut));

        informationList.add(new ServiceInformation("Beard Trimming: 90 kr",R.drawable.beard));

        informationList.add(new ServiceInformation("Kid's haircut: 89 kr.", R.drawable.kid));

        informationList.add(new ServiceInformation("Womens Haircut: 129 kr.",R.drawable.womenscut));

        informationList.add(new ServiceInformation("Bangs: 129 kr.",R.drawable.bangs));

        informationList.add(new ServiceInformation("Style eyebrows: 60 kr",R.drawable.brow));

        informationList.add(new ServiceInformation("Colouring: from 450 kr", R.drawable.haircolor));

        informationList.add(new ServiceInformation("Streaks: from 450 kr.", R.drawable.hairstreaks));



        ServiceAdapter adapter = new ServiceAdapter(informationList, this);

        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }


    public void onClick(int position) {
        Toast.makeText(getContext(),"Position:",Toast.LENGTH_LONG).show();
    }
}