package com.example.bookroom.history;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.bookroom.Common.Social;
import com.example.bookroom.home.SocialDatabase;

import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {
    ArrayList<Social> socialArrayList;
    MutableLiveData<List<Social>> listMutableLiveData = new MutableLiveData<>();
    Application application;
    HistoryDatabase historyDatabase;

    public HistoryRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Social>> getSocial() {
        historyDatabase = HistoryDatabase.getInstance(application);
        socialArrayList = historyDatabase.getAll();
        if(socialArrayList.size()==0)
        {
            socialArrayList=new ArrayList<>();

        }
        listMutableLiveData.postValue(socialArrayList);
        return listMutableLiveData;
    }
}
