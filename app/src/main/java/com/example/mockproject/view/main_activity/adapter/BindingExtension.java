package com.example.mockproject.view.main_activity.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.mockproject.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.concurrent.TimeUnit;

public class BindingExtension {
    @BindingAdapter({"app:imageSong"})
    public static void setImageSong(ShapeableImageView imageSong, String uri) {
        Bitmap bitmap = getBitmap(uri);
        if (bitmap == null) {
            imageSong.setImageResource(R.drawable.ic_song);
            imageSong.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else {
            imageSong.setImageBitmap(bitmap);
        }
    }

    @BindingAdapter({"setResourceImageArtist"})
    public static void setImageArtist(ImageView img, int srcImage) {
        img.setImageResource(srcImage);
    }

    public static Bitmap getBitmap(String uri) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        byte[] rawArt;
        Bitmap art;
        BitmapFactory.Options bfo = new BitmapFactory.Options();
        if (uri.charAt(0) == '/') {
            try {
                mmr.setDataSource(uri);
                rawArt = mmr.getEmbeddedPicture();
                if (null != rawArt) {
                    art = BitmapFactory.decodeByteArray(rawArt, 0, rawArt.length, bfo);
                    return art;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }


    @BindingAdapter({"app:setDurationText"})
    public static void setDurationText(TextView textView, long duration) {
        textView.setText(String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))));
    }

}
