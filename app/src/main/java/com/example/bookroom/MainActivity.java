package com.example.bookroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.bookroom.Common.FragmentNavigate;
import com.example.bookroom.love.LoveHistoryFragment;
import com.example.bookroom.databinding.ActivityMainBinding;
import com.example.bookroom.history.HistoryFragment;
import com.example.bookroom.home.HomeFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        HomeFragment homeFragment = HomeFragment.newInstance();
        FragmentNavigate.addNewFragmentMain(this, homeFragment);
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homeFragment = HomeFragment.newInstance();
                FragmentNavigate.addNewFragmentMain(MainActivity.this, homeFragment);
            }
        });
        binding.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryFragment historyFragment = HistoryFragment.newInstance();
                FragmentNavigate.addNewFragmentMain(MainActivity.this, historyFragment);
            }
        });
        binding.btnLoveHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoveHistoryFragment loveHistoryFragment=LoveHistoryFragment.newInstance();
                FragmentNavigate.addNewFragmentMain(MainActivity.this,loveHistoryFragment);
            }
        });
    }
}