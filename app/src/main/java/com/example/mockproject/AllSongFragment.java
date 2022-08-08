package com.example.mockproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.adapter.ListSongAdapter;
import com.example.mockproject.databinding.FragmentAllSongBinding;
import com.example.mockproject.model.SongModel;
import com.example.mockproject.viewmodel.HomeViewModel;
import com.example.mockproject.viewmodel.SongViewModel;

public class AllSongFragment extends Fragment implements ListSongAdapter.OnClickItemListSong{

    private static final String TAG = "AllSongFragment";
    FragmentAllSongBinding fragmentAllSongBinding;
    SongViewModel songViewModel;

    public static AllSongFragment newInstance() {
        return new AllSongFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAllSongBinding = FragmentAllSongBinding.inflate(getLayoutInflater());
        return fragmentAllSongBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        songViewModel = new ViewModelProvider(this).get(SongViewModel.class);

        ListSongAdapter listSongAdapter = new ListSongAdapter(this,songViewModel.getListSongs(requireActivity()));
        fragmentAllSongBinding.rcvAllSongs.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL,false));
        fragmentAllSongBinding.rcvAllSongs.setAdapter(listSongAdapter);
    }

    @Override
    public void onClickItemSong(SongModel songModel, View view) {
        Log.i(TAG, "onClickItemSong: "+songModel);
    }
}