package com.example.bookroom.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bookroom.Common.Social;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    HomeRepository homeRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeRepository=new HomeRepository(application);
    }
    public LiveData<List<Social>> getAllSocial()
    {
        return homeRepository.getSocial();
    }
}
