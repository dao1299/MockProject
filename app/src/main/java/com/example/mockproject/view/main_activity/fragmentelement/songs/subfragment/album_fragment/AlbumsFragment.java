package com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.album_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.R;
import com.example.mockproject.databinding.FragmentAlbumsBinding;

import java.util.ArrayList;
import java.util.List;

public class AlbumsFragment extends Fragment {

    AlbumAdapter albumAdapter;
    FragmentAlbumsBinding fragmentAlbumsBinding;
    List<AlbumModel> albumModelList = new ArrayList<>();
    AlbumViewModel albumViewModel;

    public static AlbumsFragment newInstance() {
        return new AlbumsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAlbumsBinding = FragmentAlbumsBinding.inflate(getLayoutInflater());
        return fragmentAlbumsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        albumViewModel = new ViewModelProvider(requireActivity()).get(AlbumViewModel.class);
        albumModelList = albumViewModel.initListAlbum(requireActivity());
        fragmentAlbumsBinding.setLifecycleOwner(this);
        albumAdapter = new AlbumAdapter(albumModelList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(),2);
        fragmentAlbumsBinding.rcvAlbum.setLayoutManager(gridLayoutManager);
        fragmentAlbumsBinding.rcvAlbum.setAdapter(albumAdapter);
    }
}