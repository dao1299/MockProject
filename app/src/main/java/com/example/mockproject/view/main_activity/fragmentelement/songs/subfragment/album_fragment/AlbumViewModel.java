package com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.album_fragment;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.example.mockproject.model.SongModel;
import com.example.mockproject.utils.SongsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AlbumViewModel extends ViewModel {
    public List<AlbumModel> initListAlbum(Activity activity){
        List<SongModel> songModelList = new SongsUtils().getListSongs(activity);
        List<AlbumModel> albumModelList = new ArrayList<>();
        HashMap<AlbumModel,Integer> hashMap = new HashMap<>();
        for (SongModel x: songModelList){
            AlbumModel album = new AlbumModel(x.getAlbumId(),x.getAlbumSong(),x.getArtistSong(),0);
            if (hashMap.containsKey(x.getAlbumId())){
                int size = hashMap.get(album)+1;
                hashMap.put(album,size);
            }else{
                hashMap.put(album,1);
            }
        }
        Set<AlbumModel> keySet = hashMap.keySet();
        for (AlbumModel key : keySet) {
            if (hashMap.get(key)!=null)
                key.setNumSong(hashMap.get(key));
            else
                key.setNumSong(1);
            albumModelList.add(key);
        }
        return albumModelList;
    }
}
