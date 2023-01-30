package com.github.au556265.myprojectapplication.Repository.Services;

import androidx.lifecycle.LiveData;

import com.github.au556265.myprojectapplication.Models.Service;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ServicesRepository extends LiveData<ArrayList<Service>> {
    private DatabaseReference myRef;
    private StorageReference storageRef;
    private static ServicesRepository instance;
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
    }

    public void addServices(String name, double price, byte[] stringImage) {
        myRef.push().setValue(new Service(name,price));
        storageRef.child("/Services/"+ name + ".jpg").putBytes(stringImage);
    }
}
