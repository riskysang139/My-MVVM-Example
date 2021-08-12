package com.example.bookroom.love;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.bookroom.Common.Social;
import com.example.bookroom.home.SocialDatabase;

import java.util.ArrayList;
import java.util.List;

public class LoveRepository {
    ArrayList<Social> socialArrayList,loveHistory;
    MutableLiveData<List<Social>> listMutableLiveData = new MutableLiveData<>();
    Application application;
    SocialDatabase socialDatabase;

    public LoveRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Social>> getSocial() {
        socialArrayList=new ArrayList<>();
        socialDatabase = SocialDatabase.getInstance(application);
        socialArrayList = socialDatabase.getWithID();
        if(socialArrayList.size()==0)
        {
            socialArrayList=new ArrayList<>();
        }
//        Log.e("check",socialArrayList.toString()+"");
//        loveHistory=new ArrayList<>();
//        for(int i=0;i<socialArrayList.size();i++)
//        {
//
//            if(socialArrayList.get(i).getFlag()==1)
//            {
//                if(loveHistory.get(i).getID()!=socialArrayList.get(i).getID())
//                {
//                    loveHistory.add(socialArrayList.get(i));
//                }
//
//            }
//        }

        listMutableLiveData.postValue(socialArrayList);
        return listMutableLiveData;
    }
}
