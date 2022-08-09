package com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.artist_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.databinding.FragmentArtistsBinding;
import com.example.mockproject.viewmodel.HomeViewModel;

import java.util.List;

public class ArtistsFragment extends Fragment {

    FragmentArtistsBinding fragmentArtistsBinding;


    public static ArtistsFragment newInstance() {
        return new ArtistsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentArtistsBinding = FragmentArtistsBinding.inflate(inflater);
        return fragmentArtistsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        ArtistAdapter artistAdapter = new ArtistAdapter(homeViewModel.initListArtists());
        fragmentArtistsBinding.rcvListArtist.setAdapter(artistAdapter);
        fragmentArtistsBinding.rcvListArtist.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL,false));
    }
}