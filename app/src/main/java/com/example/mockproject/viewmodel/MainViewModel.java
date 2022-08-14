package com.example.mockproject.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mockproject.R;
import com.example.mockproject.app.MyApplication;
import com.example.mockproject.model.HomeElementModel;
import com.example.mockproject.model.PlaylistModel;
import com.example.mockproject.model.SongModel;
import com.example.mockproject.repository.SongsRepo;
import com.example.mockproject.service.PlayMediaService;
import com.example.mockproject.utils.SongsUtils;
import com.example.mockproject.view.main_activity.adapter.HotRecommendAdapter;
import com.example.mockproject.view.main_activity.adapter.ListSongAdapter;
import com.example.mockproject.view.main_activity.adapter.PlayListAdapter;
import com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.artist_fragment.ArtistModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "MainViewModel";
    List<SongModel> listSongHotRcm;
    List<SongModel> listRecentlyPlayer;
    List<PlaylistModel> listPlaylist;
    List<HomeElementModel> homeElementModels;
    Application application;
    private final SongsRepo songsRepo = SongsRepo.getInstance();
    private final MutableLiveData<Boolean> isPlayingLive = new MutableLiveData<>(songsRepo.isPlaying());
    public final LiveData<Boolean> isPlayingSong = isPlayingLive;
    public SongModel songModel;
    public Boolean isPlaying;
    Intent serviceIntent;
    MutableLiveData<SongModel> songModelMutableLiveData = new MutableLiveData<>(new SongModel(-1,"","","0","","","0","0","",""));
    LiveData<SongModel> songModelLiveData = songModelMutableLiveData;
    HandlerThread handlerThread;
    Handler handler;

    MutableLiveData<Long> currentDurationMutableLiveData = new MutableLiveData<>(0L);
    LiveData<Long> currentDurationLiveData = currentDurationMutableLiveData;

    MutableLiveData<Boolean> statusOfMediaMutableLiveData = new MutableLiveData<>(false);
    LiveData<Boolean> statusOfMediaLiveData = statusOfMediaMutableLiveData;
    MutableLiveData<Boolean> isVisibleBottomControl = new MutableLiveData<>();
    LiveData<Boolean> isVisibleBottomControlLive = isVisibleBottomControl;


    public MainViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        serviceIntent = new Intent(application.getApplicationContext(), PlayMediaService.class);
        changeData();
        setupHandler();
        statusOfMedia();
    }

    public void eventPause() {
        isPlaying = songsRepo.isPlaying();
        isPlayingLive.postValue(isPlaying);
        Log.i(TAG, "eventPause: " + songsRepo.isPlaying());
        serviceIntent.setAction(MyApplication.PLAY_PAUSE_SONG);
        getApplication().startService(serviceIntent);
    }

    public void eventNextSong() {
        Log.i(TAG, "eventNextSong: ");
        serviceIntent.setAction(MyApplication.NEXT_SONG);
        getApplication().startService(serviceIntent);
    }

    public void eventPreviousSong() {
        Log.i(TAG, "eventPreviousSong: ");
        serviceIntent.setAction(MyApplication.PREVIOUS_SONG);
        getApplication().startService(serviceIntent);
    }

    public void eventCancelService(){
        serviceIntent.setAction(MyApplication.FINISH_SERVICE);
        getApplication().startService(serviceIntent);
    }

    public List<SongModel> getListSongs(Activity context) {
        List<SongModel> songModelList = new SongsUtils().getListSongs(context);
        SongsRepo.getInstance().setAllSong(songModelList);
        return songModelList;
    }

    public void changeData() {
        SongsRepo songsRepo = SongsRepo.getInstance();
        Log.i(TAG, "changeData: ");
        songsRepo.getSongModelMutableLiveData().observeForever(new Observer<SongModel>() {
            @Override
            public void onChanged(SongModel songModel) {
                if (songModel==null){
                    cancelHandlerThread();
                }else{
                    songModelMutableLiveData.setValue(songModel);
                    Log.i(TAG, "onChanged: " + songModelMutableLiveData.getValue().getNameSong() + "  " + songModelLiveData.getValue().getNameSong());
                    updateDuration();
                }
            }
        });
        songsRepo.getPlayPauseMutable().observeForever(new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                isPlayingLive.postValue(aBoolean);
            }
        });


        songsRepo.getCurrentDuration().observeForever(new Observer<Long>() {
            @Override
            public void onChanged(Long value) {
//                Log.i(TAG, "Time: "+integer);
//                Log.i(TAG, "================================");
                currentDurationMutableLiveData.setValue(value);
            }
        });

    }

    public LiveData<SongModel> getSongModelLiveData() {
        return songModelLiveData;
    }

    public void setSongModelLiveData(LiveData<SongModel> songModelLiveData) {
        this.songModelLiveData = songModelLiveData;
    }

    private void setupHandler(){
        handlerThread = new HandlerThread("Update duration");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

    }

    private void updateDuration(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                serviceIntent.setAction(MyApplication.UPDATE_CURRENT_DURATION);
                currentDurationMutableLiveData.postValue(SongsRepo.getInstance().getCurrentDuration().getValue());
                updateDuration();
                getApplication().startService(serviceIntent);
            }
        },1000);
    }
    public LiveData<Long> getCurrentDurationLiveData() {
        return currentDurationLiveData;
    }
    public void updateSeekbar(int progress){
        serviceIntent.setAction(MyApplication.UPDATE_SEEKBAR);
        serviceIntent.putExtra("PROGRESS",progress);
        getApplication().startService(serviceIntent);
    }

    public void cancelHandlerThread(){
        handler.removeMessages(1);
        handlerThread.quitSafely();
    }

    public void statusOfMedia(){
        songsRepo.getStatusOfMediaMutableLive().observeForever(new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean status) {
                Log.i(TAG, "Status of media: "+status);
                statusOfMediaMutableLiveData.postValue(status);
            }
        });
    }

    public void setVisibilityForBottomControl(Boolean isVisible){
        isVisibleBottomControl.postValue(isVisible);
    }

    public MutableLiveData<Boolean> getIsVisibleBottomControl() {
        return isVisibleBottomControl;
    }

    public LiveData<Boolean> getIsVisibleBottomControlLive() {
        return isVisibleBottomControlLive;
    }

    public LiveData<Boolean> getStatusOfMediaLiveData() {
        return statusOfMediaLiveData;
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
}
