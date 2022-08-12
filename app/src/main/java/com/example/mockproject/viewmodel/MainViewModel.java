package com.example.mockproject.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mockproject.R;
import com.example.mockproject.app.MyApplication;
import com.example.mockproject.repository.SongsRepo;
import com.example.mockproject.service.PlayMediaService;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "MainViewModel";
    private int mPlayDrawable;


    SongsRepo songsRepo =  SongsRepo.getInstance();

    public MutableLiveData<Boolean> isPlayingLive = new MutableLiveData<>(songsRepo.isPlaying());
    public LiveData<Boolean> isPlayingSong = isPlayingLive;
    public Boolean isPlaying;
    Intent serviceIntent;

    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    public int isPlayingDrawable(){
        return mPlayDrawable;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        serviceIntent = new Intent(application.getApplicationContext(), PlayMediaService.class);
        isPlaying = true;
        mPlayDrawable = R.drawable.ic_pause;
    }

    public void eventPause(){
//        Log.i(TAG, "eventPause: "+songsRepo.isPlaying());
//        isPlaying.postValue(songsRepo.isPlaying());
        isPlaying = songsRepo.isPlaying();
        isPlayingLive.postValue(isPlaying);
        Log.i(TAG, "eventPause: "+songsRepo.isPlaying());
        serviceIntent.setAction(MyApplication.PLAY_PAUSE_SONG);
        getApplication().startService(serviceIntent);
        mPlayDrawable = R.drawable.ic_play;
    }

    public void eventNextSong(){
        Log.i(TAG, "eventNextSong: ");
        serviceIntent.setAction(MyApplication.NEXT_SONG);
        getApplication().startService(serviceIntent);
        mPlayDrawable = R.drawable.ic_pause;
    }

    public int getPlayDrawable() {
        return mPlayDrawable;
    }

    public void setPlayDrawable(int mPlayDrawable) {
        this.mPlayDrawable = mPlayDrawable;
    }

    public void eventPreviousSong(){
        Log.i(TAG, "eventPreviousSong: ");
        serviceIntent.setAction(MyApplication.PREVIOUS_SONG);
        getApplication().startService(serviceIntent);
        mPlayDrawable = R.drawable.ic_pause;
    }

    public void eventPlayingSong(){

    }
}
