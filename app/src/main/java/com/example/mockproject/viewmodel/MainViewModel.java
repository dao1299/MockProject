package com.example.mockproject.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "MainViewModel";

    public MainViewModel(@NonNull Application application) {
        super(application);
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

    public void eventPlayingSong(){

    }
}
