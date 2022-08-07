package com.example.mockproject.view.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.R;
import com.example.mockproject.adapter.ViewPagerSongsFragmentAdapter;
import com.example.mockproject.databinding.FragmentSongBinding;
import com.example.mockproject.viewmodel.SongViewModel;
import com.google.android.material.tabs.TabLayoutMediator;

public class SongFragment extends Fragment {

    private SongViewModel mViewModel;

    FragmentSongBinding fragmentSongBinding;

    public static SongFragment newInstance() {
        return new SongFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentSongBinding = FragmentSongBinding.inflate(inflater,container,false);
        return fragmentSongBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ViewPagerSongsFragmentAdapter viewPagerSongsFragmentAdapter = new ViewPagerSongsFragmentAdapter(this)
        fragmentSongBinding.viewPagerSongs.setAdapter(new ViewPagerSongsFragmentAdapter(this));
        new TabLayoutMediator(fragmentSongBinding.tabLayoutSongs,fragmentSongBinding.viewPagerSongs,(tab, position) -> {
            switch (position){
                case 0:
                    tab.setText(R.string.all_songs);
                    break;
                case 1:
                    tab.setText(R.string.Playlists);
                    break;
                case 2:
                    tab.setText(R.string.albums);
                    break;
                case 3:
                    tab.setText(R.string.artists);
                    break;
                case 4:
                    tab.setText(R.string.genres);
                    break;
            }
        }).attach();
    }

}