package com.example.mockproject.view.main_activity.fragmentelement.nowplaying;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mockproject.app.MyApplication;
import com.example.mockproject.databinding.PlaySongsBinding;
import com.example.mockproject.repository.SongsRepo;
import com.example.mockproject.service.PlayMediaService;
import com.example.mockproject.service.SingletonMedia;
import com.example.mockproject.viewmodel.MainViewModel;

public class FragmentNowPlaying extends Fragment {

    private final String TAG = "FragmentNowPlaying";

    PlaySongsBinding playSongsBinding;



    Intent intent;

    public static FragmentNowPlaying newInstance() {
        return new FragmentNowPlaying();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        playSongsBinding = PlaySongsBinding.inflate(getLayoutInflater());
        return playSongsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        playSongsBinding.setViewModel(mViewModel);
        playSongsBinding.setLifecycleOwner(this);

        mViewModel.getSongModelLiveData().observe(getViewLifecycleOwner(), songModel -> {
            playSongsBinding.breakLine.setMax((int) songModel.getDurationSong());
            Log.i(TAG, "onViewCreated: "+playSongsBinding.breakLine.getMax());
        });

        mViewModel.getCurrentDurationLiveData().observe(getViewLifecycleOwner(), value->{
//
            Log.i(TAG, "onViewCreated: "+value);
            if (value!=null) playSongsBinding.breakLine.setProgress(Math.toIntExact(value));
        });

    }


}