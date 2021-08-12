package com.example.bookroom.register;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.bookroom.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegisterRepository {
    ArrayList<RegisterUser> userArrayList;
    MutableLiveData<List<RegisterUser>> listMutableLiveData=new MutableLiveData<>();
    Application application;
    UserDatabase userDatabase;
    public RegisterRepository(Application application) {
        this.application = application;
    }
    public void insertUser(RegisterUser registerUser)
    {
        userDatabase.insertUser(registerUser);
    }
    public MutableLiveData<List<RegisterUser>> getAllUser()
    {
        if(listMutableLiveData==null)
        {
            listMutableLiveData=new MutableLiveData<>();
        }
        userDatabase=UserDatabase.getInstance(application);
        userArrayList=new ArrayList<>();
        userArrayList=userDatabase.getAll();
        listMutableLiveData.setValue(userArrayList);
        return listMutableLiveData;
    }

}
