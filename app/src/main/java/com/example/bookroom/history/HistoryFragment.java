package com.example.bookroom.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.bookroom.R;
import com.example.bookroom.databinding.FragmentHistoryBinding;
import com.example.bookroom.home.RcvHomeAdapter;

import java.util.List;

public class HistoryFragment extends Fragment {
    FragmentHistoryBinding binding;
    HistoryViewModel historyViewModel;
    HistoryDatabase historyDatabase;
    RcvHistoryAdapter rcvHistoryAdapter;
    List<Social> socialList;

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment historyFragment = new HistoryFragment();
        historyFragment.setArguments(args);
        return historyFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        View view = binding.getRoot();
        binding.executePendingBindings();
        historyViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setHistoryViewModel(historyViewModel);
        historyDatabase = HistoryDatabase.getInstance(getContext());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyViewModel.getAllSocial().observe(getActivity(), new Observer<List<Social>>() {
            @Override
            public void onChanged(List<Social> socials) {
                rcvHistoryAdapter = new RcvHistoryAdapter(socials, getContext());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5, RecyclerView.VERTICAL, false);
                binding.rcvHistory.setLayoutManager(gridLayoutManager);
                binding.rcvHistory.setAdapter(rcvHistoryAdapter);
            }
        });

    }
}