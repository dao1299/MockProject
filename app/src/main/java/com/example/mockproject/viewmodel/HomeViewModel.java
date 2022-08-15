package com.example.mockproject.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mockproject.R;
import com.example.mockproject.utils.SongsUtils;
import com.example.mockproject.view.main_activity.adapter.HotRecommendAdapter;
import com.example.mockproject.view.main_activity.adapter.ListSongAdapter;
import com.example.mockproject.view.main_activity.adapter.PlayListAdapter;
import com.example.mockproject.model.HomeElementModel;
import com.example.mockproject.model.PlaylistModel;
import com.example.mockproject.model.SongModel;
import com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.artist_fragment.ArtistModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private static final String TAG = "HomeViewModel";
    List<SongModel> listSongHotRcm;
    List<SongModel> listRecentlyPlayer;
    List<PlaylistModel> listPlaylist;
    List<HomeElementModel> homeElementModels;
    Application application;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }



    public List<ArtistModel> initListArtists(){
        List<ArtistModel> artistModels = new ArrayList<>();
        artistModels.add(new ArtistModel("Selena Gomez","20","39",R.drawable.selena_gomez));
        artistModels.add(new ArtistModel("Justin Bieber","17","22",R.drawable.justin_bieber));
        artistModels.add(new ArtistModel("Alan Walker","20","33",R.drawable.alan_walker));
        artistModels.add(new ArtistModel("Justin Bieber","33","11",R.drawable.justin_bieber));
        artistModels.add(new ArtistModel("Alan Walker","40","13",R.drawable.alan_walker));
        return artistModels;
    }

}