package com.example.bookroom.register;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookroom.login.LoginRepository;
import com.example.bookroom.login.LoginUser;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RegisterViewModel extends ViewModel {
    public MutableLiveData<String> Username = new MutableLiveData<>();
    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    public MutableLiveData<String> PasswordConfirm = new MutableLiveData<>();
    public MutableLiveData<String> strCapcha = new MutableLiveData<>();
    private MutableLiveData<RegisterUser> registerMutableLiveData;
    private List<RegisterUser> mListUser;
    private RegisterRepository registerRepository;
    private MutableLiveData<List<RegisterUser>> registerMutableLiveDataList;
    private final Executor executor = Executors.newFixedThreadPool(2);

    public MutableLiveData<RegisterUser> getUser() {

        if (registerMutableLiveData == null) {
            registerMutableLiveData = new MutableLiveData<>();
        }
        return registerMutableLiveData;

    }

    public void onClick(View view) {

        RegisterUser registerUser = new RegisterUser(EmailAddress.getValue(), Password.getValue(), Username.getValue(), PasswordConfirm.getValue(), strCapcha.getValue());
        registerMutableLiveData.postValue(registerUser);

    }

}
