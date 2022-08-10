package com.example.mockproject.view.main_activity.fragmentelement.nowplaying;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.R;

public class FragmentNowPlaying extends Fragment {

    private FragmentNowPlayingViewModel mViewModel;

    public static FragmentNowPlaying newInstance() {
        return new FragmentNowPlaying();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.play_songs, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentNowPlayingViewModel.class);
        // TODO: Use the ViewModel
    }

}