package com.example.mockproject.view.main_activity.fragmentelement.nowplaying;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.R;
import com.example.mockproject.databinding.PlaySongsBinding;
import com.example.mockproject.model.SongModel;
import com.example.mockproject.repository.SongsRepo;
import com.example.mockproject.viewmodel.MainViewModel;

public class FragmentNowPlaying extends Fragment {

    private final String TAG = "FragmentNowPlaying";
    PlaySongsBinding playSongsBinding;

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
            Log.i(TAG, "onViewCreated: "+songModel);
        });
    }

}