package com.example.mockproject.extension;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.IdRes;
import androidx.databinding.BindingAdapter;

import com.example.mockproject.R;
import com.google.android.material.imageview.ShapeableImageView;

public class BindingExtension {
    @BindingAdapter({"android:src"})
    public static void setImageSong(ShapeableImageView imageSong,String uri){
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        byte[] rawArt;
        Bitmap art;
        BitmapFactory.Options bfo=new BitmapFactory.Options();
        if (uri.charAt(0)=='/'){
            mmr.setDataSource(uri);
            rawArt = mmr.getEmbeddedPicture();
            if (null != rawArt){
                art = BitmapFactory.decodeByteArray(rawArt, 0, rawArt.length, bfo);
                imageSong.setImageBitmap(art);
            }
        }else{
            imageSong.setImageResource(R.drawable.ic_song);
        }

    }
}
