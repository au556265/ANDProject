package com.github.au556265.myprojectapplication.UI.User;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.au556265.myprojectapplication.Repository.User.UserRepository;
import com.github.au556265.myprojectapplication.Models.User;

public class RegisterUserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public RegisterUserViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
    }
    public void init(){
        userRepository.init();
    }

    public void addUser(User user){
        userRepository.addUser(user);
    }



}
