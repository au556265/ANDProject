package com.github.au556265.myprojectapplication.Repository.Services;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.Models.Service;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.internal.Sleeper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServicesRepository implements IServiceRepository {
    private DatabaseReference myRef;
    private StorageReference storageRef;
    private static ServicesRepository instance;

    private final MutableLiveData<List<Service>> searchedServices = new MutableLiveData<>();

    private final ArrayList<Service> services = new ArrayList<>();

    private ServicesRepository(){
    }

    public static synchronized ServicesRepository getInstance() {
        if(instance == null)
            instance = new ServicesRepository();
        return instance;
    }

    public void init() {
        myRef = FirebaseDatabase.getInstance("https://myprojectapplication-32774-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Services");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        serviceDataChangeListener();
    }

    public void addServices(String name, int price, byte[] stringImage) {
        myRef.push().setValue(new Service(name,price));
        storageRef.child("/Services/"+ name + ".jpg").putBytes(stringImage);
    }

    public void serviceDataChangeListener(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                services.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    keys.add(postSnapshot.getKey());
                    Service service = postSnapshot.getValue(Service.class);
                    service.setId(postSnapshot.getKey());
                    StorageReference child = storageRef.child("Services/" + service.getName() + ".jpg");
                    child.getDownloadUrl().addOnSuccessListener(uri -> service.setUri(uri));
                    services.add(service);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //log error
            }
        });
        searchedServices.setValue(services);
    }


    public LiveData<List<Service>> getSearchedServices() {
        return searchedServices;
    }
}

