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
        Bitmap bitmap = getBitmap(uri);
        if (bitmap==null){
            imageSong.setImageResource(R.drawable.ic_song);
        }else{
            imageSong.setImageBitmap(bitmap);
        }
    }

    @BindingAdapter({"setResourceImageArtist"})
    public static void setImageArtist(ImageView img, int srcImage){
        img.setImageResource(srcImage);
    }

    public static Bitmap getBitmap(String uri){
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
                    return art;
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }else{
            return null;
        }
        return null;
    }
}
