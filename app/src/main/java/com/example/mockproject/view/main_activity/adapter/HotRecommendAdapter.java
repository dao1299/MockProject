package com.example.mockproject.view.main_activity.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.BR;
import com.example.mockproject.R;
import com.example.mockproject.databinding.LayoutItemHotRecommendedBinding;
import com.example.mockproject.model.SongModel;

import java.util.List;

public class HotRecommendAdapter extends RecyclerView.Adapter<HotRecommendAdapter.ViewHolder>{

    List<SongModel> songModelList;

    public HotRecommendAdapter(List<SongModel> songModels) {
        this.songModelList = songModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemHotRecommendedBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.layout_item_hot_recommended,
                parent,
                false
        );

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            SongModel songModel = songModelList.get(position);
            holder.bind(songModel);
    }

    @Override
    public int getItemCount() {
        return songModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public LayoutItemHotRecommendedBinding binding;

        public ViewHolder(LayoutItemHotRecommendedBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj){
            binding.setVariable(BR.songModel,obj);
            binding.executePendingBindings();
        }
    }


}
