package com.example.mockproject.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PlayMediaService extends Service {

    private final IBinder playerBinder = new MusicBinder();




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return playerBinder;
    }

    public class MusicBinder extends Binder{
        public PlayMediaService getService(){
            return PlayMediaService.this;
        }
    }
}
