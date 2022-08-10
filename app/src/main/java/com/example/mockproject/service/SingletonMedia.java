package com.example.mockproject.service;

import android.media.MediaPlayer;

public class SingletonMedia {
    private static MediaPlayer mediaPlayer;
    private SingletonMedia(){
        mediaPlayer = new MediaPlayer();
    }
    public MediaPlayer getInstance(){
        if (mediaPlayer==null){
            new SingletonMedia();
        }
        return mediaPlayer;
    }
}
