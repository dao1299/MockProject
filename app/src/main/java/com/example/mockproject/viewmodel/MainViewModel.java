package com.example.mockproject.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mockproject.app.MyApplication;
import com.example.mockproject.model.SongModel;
import com.example.mockproject.repository.SongsRepo;
import com.example.mockproject.service.PlayMediaService;
import com.example.mockproject.utils.SongsUtils;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "MainViewModel";
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


    public MainViewModel(@NonNull Application application) {
        super(application);
        serviceIntent = new Intent(application.getApplicationContext(), PlayMediaService.class);
        changeData();
        setupHandler();
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
                songModelMutableLiveData.setValue(songModel);
                Log.i(TAG, "onChanged: " + songModelMutableLiveData.getValue().getNameSong() + "  " + songModelLiveData.getValue().getNameSong());
                updateDuration();
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
}
