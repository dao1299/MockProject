package com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.artist_fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mockproject.BR;
import com.example.mockproject.databinding.LayoutItemArtistBinding;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder>{
    List<ArtistModel> artistModelList;

    public ArtistAdapter(List<ArtistModel> artistModelList) {
        this.artistModelList = artistModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutItemArtistBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArtistModel artistModel = artistModelList.get(position);
        if (artistModel!=null){
            holder.layoutItemArtistBinding.setModel(artistModel);
            holder.layoutItemArtistBinding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return artistModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemArtistBinding layoutItemArtistBinding;

        public ViewHolder(LayoutItemArtistBinding layoutItemArtistBinding) {
            super(layoutItemArtistBinding.getRoot());
            this.layoutItemArtistBinding = layoutItemArtistBinding;
        }

    }


}
