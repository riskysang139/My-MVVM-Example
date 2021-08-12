package com.example.bookroom.login;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.bookroom.UserDatabase;
import com.example.bookroom.register.RegisterUser;

import java.util.ArrayList;
import java.util.List;

public class LoginRepository {
    ArrayList<RegisterUser> userArrayList;
    MutableLiveData<List<RegisterUser>> listMutableLiveData=new MutableLiveData<>();
    Application application;
    UserDatabase userDatabase;
    public LoginRepository(Application application) {
        this.application = application;
    }
    public MutableLiveData<List<RegisterUser>> getAllUser()
    {
        userDatabase=UserDatabase.getInstance(application);
        userArrayList=userDatabase.getAll();
        listMutableLiveData.setValue(userArrayList);
        return listMutableLiveData;
    }
}

