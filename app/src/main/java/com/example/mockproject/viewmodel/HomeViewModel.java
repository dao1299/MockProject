package com.example.mockproject.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mockproject.R;
import com.example.mockproject.adapter.HotRecommendAdapter;
import com.example.mockproject.adapter.ListSongAdapter;
import com.example.mockproject.adapter.PlayListAdapter;
import com.example.mockproject.model.HomeElementModel;
import com.example.mockproject.model.PlaylistModel;
import com.example.mockproject.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    List<SongModel> listSongHotRcm;
    List<SongModel> listRecentlyPlayer;
    List<PlaylistModel> listPlaylist;

    List<HomeElementModel> homeElementModels;
    Application application;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }


    public List<HomeElementModel> initHomeElement(){
        homeElementModels = new ArrayList<>();
        homeElementModels.add(new HomeElementModel(application.getString(R.string.HotRCM),
                false,
                initHotAdapter()));

        homeElementModels.add(new HomeElementModel(application.getString(R.string.Playlist),
                true,
                initPlaylistAdapter()));

        homeElementModels.add(new HomeElementModel(application.getString(R.string.RecentlyPlayed),true,initListSongAdapter()));
        return homeElementModels;
    }

    private void initData(){





        listRecentlyPlayer.add(new SongModel(1,"Sound of Sky","Dilon Bruce","Dilon Bruce"));
        listRecentlyPlayer.add(new SongModel(2,"Girl on Fire","Dilon Bruce","Alecia Keys"));
        listRecentlyPlayer.add(new SongModel(3,"Sound of Sky","Dilon Bruce","Dilon Bruce"));
        listRecentlyPlayer.add(new SongModel(4,"Girl on Fire","Dilon Bruce","Alecia Keys"));
    }

    public HotRecommendAdapter initHotAdapter(){
        listSongHotRcm = new ArrayList<>();
        listSongHotRcm.add(new SongModel(1,"Sound of Sky","Dilon Bruce","Dilon Bruce"));
        listSongHotRcm.add(new SongModel(2,"Girl on Fire","Dilon Bruce","Alecia Keys"));
        listSongHotRcm.add(new SongModel(3,"Sound of Sky","Dilon Bruce","Dilon Bruce"));
        listSongHotRcm.add(new SongModel(4,"Girl on Fire","Dilon Bruce","Alecia Keys"));

        return new HotRecommendAdapter(listSongHotRcm);
    }

    public PlayListAdapter initPlaylistAdapter(){
        listPlaylist = new ArrayList<>();
        listPlaylist.add(new PlaylistModel("Classic Playlist","1","Piano Guys"));
        listPlaylist.add(new PlaylistModel("Summer Playlist","2","Dilon Bruce"));
        listPlaylist.add(new PlaylistModel("Classic Playlist","1","Piano Guys"));
        listPlaylist.add(new PlaylistModel("Summer Playlist","2","Dilon Bruce"));
        return new PlayListAdapter(listPlaylist);
    }

    public ListSongAdapter initListSongAdapter(){
        listRecentlyPlayer = new ArrayList<>();
        listRecentlyPlayer.add(new SongModel(1,"Sound of Sky","Dilon Bruce","Dilon Bruce"));
        listRecentlyPlayer.add(new SongModel(2,"Girl on Fire","Dilon Bruce","Alecia Keys"));
        listRecentlyPlayer.add(new SongModel(3,"Sound of Sky","Dilon Bruce","Dilon Bruce"));
        listRecentlyPlayer.add(new SongModel(4,"Girl on Fire","Dilon Bruce","Alecia Keys"));
        return new ListSongAdapter( null,listRecentlyPlayer);
    }
}