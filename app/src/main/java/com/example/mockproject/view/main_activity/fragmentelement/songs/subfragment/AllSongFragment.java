package com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.R;
import com.example.mockproject.databinding.FragmentAllSongBinding;
import com.example.mockproject.repository.SongsRepo;
import com.example.mockproject.service.PlayMediaService;
import com.example.mockproject.view.main_activity.adapter.ListSongAdapter;
import com.example.mockproject.model.SongModel;
import com.example.mockproject.viewmodel.SongViewModel;

import java.util.List;

public class AllSongFragment extends Fragment implements ListSongAdapter.OnClickItemListSong {
    private static final int KIND_OF_LIST_SONG = 1;
    private static final String TAG = "AllSongFragment";
    FragmentAllSongBinding fragmentAllSongBinding;
    SongViewModel songViewModel;
    SongsRepo songsRepo = SongsRepo.getInstance();
    List<SongModel> songModelList;

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
        songModelList = songViewModel.getListSongs(requireActivity());
        ListSongAdapter listSongAdapter = new ListSongAdapter(this,songModelList);
        fragmentAllSongBinding.rcvAllSongs.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
        fragmentAllSongBinding.rcvAllSongs.setAdapter(listSongAdapter);
    }

    @Override
    public void onClickItemSong(SongModel songModel, View view) {
        songsRepo.setSongModelList(songModelList);
        if (songsRepo.getKindOfListSongs()!=KIND_OF_LIST_SONG){
            songsRepo.setKindOfListSongs(KIND_OF_LIST_SONG);
            songsRepo.setSongModelList(songModelList);
        };
        int index = songModelList.indexOf(songModel);
        songsRepo.setCurrentSongIndex(index);
        Log.i(TAG, "onClickItemSong: "+index+" : "+songModel);
        Intent intent = new Intent(requireActivity(),PlayMediaService.class);

        requireActivity().startService(intent);

    }
}