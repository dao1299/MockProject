package com.example.mockproject.view.main_activity.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

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
            try{
                mmr.setDataSource(uri);
                rawArt = mmr.getEmbeddedPicture();
                if (null != rawArt){
                    art = BitmapFactory.decodeByteArray(rawArt, 0, rawArt.length, bfo);
                    imageSong.setImageBitmap(art);
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }else{
            imageSong.setImageResource(R.drawable.ic_song);
        }

    }

    @BindingAdapter({"setResourceImageArtist"})
    public static void setImageArtist(ImageView img, int srcImage){
        img.setImageResource(srcImage);
    }
}
