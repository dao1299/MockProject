package com.example.mockproject.view.main_activity.fragmentelement.nowplaying;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mockproject.databinding.PlaySongsBinding;
import com.example.mockproject.viewmodel.MainViewModel;


public class FragmentNowPlaying extends Fragment {

    private final String TAG = "FragmentNowPlaying";

    MainViewModel mViewModel;
    PlaySongsBinding playSongsBinding;

    public static FragmentNowPlaying newInstance() {
        return new FragmentNowPlaying();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        playSongsBinding = PlaySongsBinding.inflate(getLayoutInflater());
        playSongsBinding.setLifecycleOwner(this);
        return playSongsBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel  = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playSongsBinding.setViewModel(mViewModel);
    }


    @Override
    public void onStart() {
        mViewModel.setVisibilityForBottomControl(false);
        super.onStart();
    }
}