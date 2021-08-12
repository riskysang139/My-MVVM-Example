package com.example.bookroom.love;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bookroom.Common.Social;
import com.example.bookroom.home.HomeRepository;

import java.util.List;

public class LoveHistoryViewModel extends AndroidViewModel {
    LoveRepository loveRepository;

    public LoveHistoryViewModel(@NonNull Application application) {
        super(application);
        loveRepository=new LoveRepository(application);
    }
    public LiveData<List<Social>> getAllSocial()
    {
        return loveRepository.getSocial();
    }
}
