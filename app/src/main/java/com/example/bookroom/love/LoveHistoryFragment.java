package com.example.bookroom.love;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookroom.Common.Social;
import com.example.bookroom.R;
import com.example.bookroom.databinding.FragmentLoveHistoryBinding;
import com.example.bookroom.history.HistoryDatabase;
import com.example.bookroom.history.HistoryViewModel;
import com.example.bookroom.history.RcvHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoveHistoryFragment extends Fragment {
    FragmentLoveHistoryBinding binding;
    LoveHistoryViewModel loveHistoryViewModel;
    RcvLoveHistoryAdapter rcvHistoryAdapter;
    List<Social> socialArrayList;

    public static LoveHistoryFragment newInstance() {
        Bundle args = new Bundle();
        LoveHistoryFragment loveHistoryFragment = new LoveHistoryFragment();
        loveHistoryFragment.setArguments(args);
        return loveHistoryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_love_history, container, false);
        View view = binding.getRoot();
        binding.executePendingBindings();
        loveHistoryViewModel = ViewModelProviders.of(getActivity()).get(LoveHistoryViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setLoveHomeViewModel(loveHistoryViewModel);
        socialArrayList=new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loveHistoryViewModel.getAllSocial().observe(getActivity(), new Observer<List<Social>>() {
            @Override
            public void onChanged(List<Social> socials) {
                socialArrayList=socials;
                rcvHistoryAdapter = new RcvLoveHistoryAdapter(socialArrayList, getContext());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5, RecyclerView.VERTICAL, false);
                binding.rcvlove.setLayoutManager(gridLayoutManager);
                binding.rcvlove.setAdapter(rcvHistoryAdapter);
            }
        });

    }
}