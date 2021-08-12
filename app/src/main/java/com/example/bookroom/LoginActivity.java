package com.example.bookroom;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookroom.Common.FragmentNavigate;
import com.example.bookroom.login.LoginFragment;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginFragment fragmentLogin = LoginFragment.newInstance();
        FragmentNavigate.addNewFragment(this, fragmentLogin);
    }
}
