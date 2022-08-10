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

    public List<SongModel> getListSongs(Activity context){
        return new SongsUtils().getListSongs(context);
    }


    public List<HomeElementModel> initHomeElement(){
        homeElementModels = new ArrayList<>();
        homeElementModels.add(new HomeElementModel(application.getString(R.string.HotRCM),
                false,
                initHotAdapter()));

        homeElementModels.add(new HomeElementModel(application.getString(R.string.Playlists),
                true,
                initPlaylistAdapter()));

        homeElementModels.add(new HomeElementModel(application.getString(R.string.RecentlyPlayed),true,initListSongAdapter()));
        return homeElementModels;
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

    public List<ArtistModel> initListArtists(){
        List<ArtistModel> artistModels = new ArrayList<>();
        artistModels.add(new ArtistModel("Selena Gomez","20","39",R.drawable.selena_gomez));
        artistModels.add(new ArtistModel("Justin Bieber","17","22",R.drawable.justin_bieber));
        artistModels.add(new ArtistModel("Alan Walker","20","33",R.drawable.alan_walker));
        artistModels.add(new ArtistModel("Justin Bieber","33","11",R.drawable.justin_bieber));
        artistModels.add(new ArtistModel("Alan Walker","40","13",R.drawable.alan_walker));
        return artistModels;
    }

    public void eventPause(){
        Log.i(TAG, "eventPause: ");
    }

    public void eventNextSong(){
        Log.i(TAG, "eventNextSong: ");
    }

    public void eventPreviousSong(){
        Log.i(TAG, "eventPreviousSong: ");
    }
}