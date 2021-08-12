package com.example.bookroom.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookroom.Common.Social;
import com.example.bookroom.history.HistoryDatabase;
import com.example.bookroom.MainActivity;
import com.example.bookroom.R;
import com.example.bookroom.Webview.WebViewActivity;
import com.example.bookroom.databinding.FragmentHomeBinding;

import java.io.Serializable;
import java.util.List;

public class HomeFragment extends Fragment {
    private MainActivity mainActivity;
    FragmentHomeBinding binding;
    List<Social> socialList, historyList;
    HomeViewModel homeViewModel;
    RcvHomeAdapter rcvHomeAdapter;
    HistoryDatabase historyDatabase;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel.getAllSocial().observe(getActivity(), new Observer<List<Social>>() {
            @Override
            public void onChanged(List<Social> socials) {
                socialList = socials;
                for(int i=0;i<socialList.size();i++)
                {
                    if(i % 5==0)
                    {
                        socialList.add(i,new Social());
                    }
                }
                rcvHomeAdapter = new RcvHomeAdapter(socialList, getContext(), new RcvHomeAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(Social social) {
                        historyDatabase.insertSocial(social);
                        onClickGoToWebView(social);
                    }
                });
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5, RecyclerView.VERTICAL, false);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                binding.rcvAdd.setLayoutManager(layoutManager);
                binding.rcvAdd.setAdapter(rcvHomeAdapter);
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = binding.getRoot();
        mainActivity = (MainActivity) getActivity();
        binding.executePendingBindings();
        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setHomeViewModel(homeViewModel);
        historyDatabase = HistoryDatabase.getInstance(getContext());
        return view;

    }

    public void onClickGoToWebView(Social social) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("link", (Serializable) social);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }
}
