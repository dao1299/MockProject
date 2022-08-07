package com.example.mockproject.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mockproject.AlbumsFragment;
import com.example.mockproject.AllSongFragment;
import com.example.mockproject.ArtistsFragment;
import com.example.mockproject.GenresFragment;
import com.example.mockproject.PlayListFragment;
import com.example.mockproject.view.fragment.SongFragment;

public class ViewPagerSongsFragmentAdapter extends FragmentStateAdapter {

    public ViewPagerSongsFragmentAdapter(@NonNull Fragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return AllSongFragment.newInstance();
            case 1:
                return PlayListFragment.newInstance();
            case 2:
                return AlbumsFragment.newInstance();
            case 3:
                return ArtistsFragment.newInstance();
            case 4:
                return GenresFragment.newInstance();
            default:
                return AllSongFragment.newInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
