package com.example.bookroom.login;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bookroom.register.RegisterUser;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    LoginRepository loginRepository;
    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<LoginUser> userMutableLiveData;

    public MutableLiveData<LoginUser> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }
    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository=new LoginRepository(application);
    }
    public LiveData<List<RegisterUser>> getAllUser()
    {
        return loginRepository.getAllUser();
    }
    public void onClick(View view) {

        LoginUser loginUser = new LoginUser(EmailAddress.getValue(), Password.getValue());

        userMutableLiveData.setValue(loginUser);

    }
}
