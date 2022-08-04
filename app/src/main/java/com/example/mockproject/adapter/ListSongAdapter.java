package com.example.mockproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.databinding.LayoutItemListSongBinding;
import com.example.mockproject.model.SongModel;

public class ListSongAdapter extends ListAdapter<SongModel, ListSongAdapter.ViewHolder> {
    private OnClickItemListSong onClickItemListSong;

    public ListSongAdapter(@NonNull DiffUtil.ItemCallback<SongModel> diffCallback, OnClickItemListSong onClickItemListSong) {
        super(diffCallback);
        this.onClickItemListSong = onClickItemListSong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutItemListSongBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongModel songModel = getItem(position);
        if (songModel != null) {
            holder.binding.setSongModel(songModel);
            holder.binding.setListener(onClickItemListSong);
            holder.binding.executePendingBindings();
        }
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
