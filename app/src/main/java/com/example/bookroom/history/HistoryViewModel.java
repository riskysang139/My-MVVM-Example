package com.example.bookroom.history;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bookroom.Common.Social;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    HistoryRepository historyRepository;
    public HistoryViewModel(@NonNull Application application) {
        super(application);
        historyRepository=new HistoryRepository(application);
    }
    public LiveData<List<Social>> getAllSocial()
    {
        return historyRepository.getSocial();
    }
}
