package com.example.mockproject.view.main_activity.fragmentelement.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.databinding.FragmentHomeBinding;
import com.example.mockproject.view.main_activity.adapter.HomeElementAdapter;
import com.example.mockproject.view.main_activity.adapter.ListSongAdapter;
import com.example.mockproject.model.SongModel;
import com.example.mockproject.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment implements ListSongAdapter.OnClickItemListSong {

    private HomeViewModel mViewModel;

    private FragmentHomeBinding fragmentHomeBinding;
    private HomeElementAdapter homeElementAdapter;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeElementAdapter = new HomeElementAdapter(mViewModel.initHomeElement(),requireActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL,false);
        fragmentHomeBinding.rcvHome.setLayoutManager(linearLayoutManager);
        fragmentHomeBinding.rcvHome.setAdapter(homeElementAdapter);

    }

    @Override
    public void onClickItemSong(SongModel songModel, View view) {

    }
}