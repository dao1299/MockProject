package com.example.mockproject.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.databinding.LayoutItemListSongBinding;
import com.example.mockproject.model.SongModel;

import java.util.List;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder>{
    private static final String TAG = "ListSongAdapter";
    private OnClickItemListSong onClickItemListSong;
    List<SongModel> songModelList;

    public ListSongAdapter(OnClickItemListSong onClickItemListSong, List<SongModel> songModelList) {
        this.onClickItemListSong = onClickItemListSong;
        this.songModelList = songModelList;
    }

    //    public ListSongAdapter(@NonNull DiffUtil.ItemCallback<SongModel> diffCallback, OnClickItemListSong onClickItemListSong) {
//        super(diffCallback);
//        this.onClickItemListSong = onClickItemListSong;
//    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        return new ViewHolder(LayoutItemListSongBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongModel songModel = songModelList.get(position);
        Log.i(TAG, "onBindViewHolder: "+songModel);
        if (songModel != null) {
            holder.binding.setSongModel(songModel);
            holder.binding.setListener(onClickItemListSong);
            holder.binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return songModelList.size();
    }

    public interface OnClickItemListSong {
        void onClickItemSong(SongModel songModel, View view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemListSongBinding binding;

        public ViewHolder(LayoutItemListSongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
