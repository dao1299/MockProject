package com.example.mockproject.service;

import android.media.MediaPlayer;

import com.example.mockproject.model.SongModel;

import java.util.List;

public class SingletonMedia {

    MediaPlayer mediaPlayer;
    private static volatile SingletonMedia instance = null;
    private SingletonMedia() { }

    public static SingletonMedia getInstance() {
        if (instance == null) {
            synchronized (SingletonMedia.class) {
                if (instance == null) {
                    instance = new SingletonMedia();
                }
            }
        }

        return instance;
    }

}
