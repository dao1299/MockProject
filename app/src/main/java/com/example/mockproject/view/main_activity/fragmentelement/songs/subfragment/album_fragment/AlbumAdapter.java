package com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.album_fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.databinding.LayoutItemAlbumBinding;
import com.example.mockproject.databinding.LayoutItemArtistBinding;
import com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.artist_fragment.ArtistAdapter;
import com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.artist_fragment.ArtistModel;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    List<AlbumModel> albumModels;

    public AlbumAdapter(List<AlbumModel> albumModels) {
        this.albumModels = albumModels;
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumAdapter.ViewHolder(LayoutItemAlbumBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {
        AlbumModel albumModel = albumModels.get(position);
        if (albumModel!=null){
            holder.layoutItemAlbumBinding.setModel(albumModel);
            holder.layoutItemAlbumBinding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return albumModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemAlbumBinding layoutItemAlbumBinding;

        public ViewHolder(LayoutItemAlbumBinding layoutItemAlbumBinding) {
            super(layoutItemAlbumBinding.getRoot());
            this.layoutItemAlbumBinding = layoutItemAlbumBinding;
        }

    }
}
