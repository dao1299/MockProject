package com.example.mockproject.view.main_activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.BR;
import com.example.mockproject.R;
import com.example.mockproject.databinding.RecyclerViewFragmentHomeBinding;
import com.example.mockproject.model.HomeElementModel;

import java.util.List;

public class HomeElementAdapter extends RecyclerView.Adapter<HomeElementAdapter.ViewHolder> {
    private static final String TAG = "HomeElementAdapter";
    List<HomeElementModel> homeElementModels;
    Context context;

    public HomeElementAdapter(List<HomeElementModel> homeElementModels, Context context) {
        this.homeElementModels = homeElementModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewFragmentHomeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycler_view_fragment_home,
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeElementModel homeElementModel = homeElementModels.get(position);
        Log.i(TAG, "onBindViewHolder: " + homeElementModel);
        holder.bind(homeElementModel);
        holder.binding.rcvElementFragmentHome.setAdapter((RecyclerView.Adapter) homeElementModel.getAdapter());
        if (context.getString(R.string.HotRCM).equals(homeElementModel.getTitle())
        || context.getString(R.string.Playlists).equals(homeElementModel.getTitle())){
            holder.binding.rcvElementFragmentHome.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        }else if (context.getString(R.string.RecentlyPlayed).equals(homeElementModel.getTitle())){
            holder.binding.rcvElementFragmentHome.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        }
    }

    @Override
    public int getItemCount() {
        return homeElementModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewFragmentHomeBinding binding;

        public ViewHolder(RecyclerViewFragmentHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
            binding.setVariable(BR.HomeElementModel, obj);

            binding.executePendingBindings();
        }
    }
}