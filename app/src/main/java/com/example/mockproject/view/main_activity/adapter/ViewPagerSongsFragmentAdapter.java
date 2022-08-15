package com.example.mockproject.view.main_activity.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.AllSongFragment;
import com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.GenresFragment;
import com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.PlayListFragment;
import com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.album_fragment.AlbumsFragment;
import com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.artist_fragment.ArtistsFragment;

public class ViewPagerSongsFragmentAdapter extends FragmentStateAdapter {

    public ViewPagerSongsFragmentAdapter(@NonNull Fragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
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
