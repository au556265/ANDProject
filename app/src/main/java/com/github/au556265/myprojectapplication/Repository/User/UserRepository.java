package com.github.au556265.myprojectapplication.Repository.User;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.firebase.ui.auth.AuthUI;
import com.github.au556265.myprojectapplication.Models.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository{
    private DatabaseReference myRef;
    private static UserRepository instance;
    private final Application app;
    private final UserLiveData currentUser;
    private UserRepository(Application app){
        this.app=app;
        currentUser = new UserLiveData();
    }

    public static synchronized UserRepository getInstance(Application app) {
        if(instance == null)
            instance = new UserRepository(app);
        return instance;
    }
    public void init() {
        myRef = FirebaseDatabase.getInstance("https://myprojectapplication-32774-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("users");
    }

    //adding user to
    public void addUser(User user){
        myRef.push().setValue(user);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void logOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }
}
