package com.example.mockproject.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.mockproject.model.SongModel;
import com.example.mockproject.utils.SongsUtils;

import java.util.List;

public class SongViewModel extends ViewModel {
    public List<SongModel> getListSongs(Context context){
        return new SongsUtils().getSongs(context.getContentResolver());
    }
}