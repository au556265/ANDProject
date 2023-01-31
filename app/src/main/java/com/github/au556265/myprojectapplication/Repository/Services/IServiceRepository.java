package com.github.au556265.myprojectapplication.Repository.Services;

import androidx.lifecycle.LiveData;

import com.github.au556265.myprojectapplication.Models.Service;

import java.util.List;

public interface IServiceRepository {
    void init();
    void addServices(String name, int price, byte[] stringImage);
    LiveData<List<Service>> getSearchedServices();
}
