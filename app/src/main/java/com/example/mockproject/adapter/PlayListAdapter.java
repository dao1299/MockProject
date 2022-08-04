package com.example.mockproject.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.BR;
import com.example.mockproject.R;
import com.example.mockproject.databinding.LayoutItemPlaylistBinding;
import com.example.mockproject.model.PlaylistModel;

import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder>{

    List<PlaylistModel> playlistModelList;

    public PlayListAdapter(List<PlaylistModel> playlistModelList) {
        this.playlistModelList = playlistModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemPlaylistBinding layoutItemPlaylistBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.layout_item_playlist,
                parent,
                false
        );
        return new ViewHolder(layoutItemPlaylistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaylistModel playlistModel = playlistModelList.get(position);
        holder.bind(playlistModel);
    }

    @Override
    public int getItemCount() {
        return playlistModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public LayoutItemPlaylistBinding binding;

        public ViewHolder(LayoutItemPlaylistBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj){
            binding.setVariable(BR.songModel,obj);
            binding.executePendingBindings();
        }


    }
}
