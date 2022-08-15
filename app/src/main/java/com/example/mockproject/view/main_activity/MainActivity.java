package com.example.mockproject.view.main_activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mockproject.R;
import com.example.mockproject.databinding.ActivityMainBinding;
import com.example.mockproject.viewmodel.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int READ_EXTERNAL_STORAGE = 1;
    NavHostFragment navHostFragment;
    NavController navController;
    ActivityMainBinding activityMainBinding;
    BottomNavigationView bottomNavigationView;
    MainViewModel mainViewModel;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        activityMainBinding.setViewModel(mainViewModel);
        activityMainBinding.setLifecycleOwner(this);
        setupBottomNavigation();
        setupAppbar();
        eventDetailPlayingSong();
        initSeekbar();
        updateSeekbar();
    }

    private boolean hasStoragePermission() {
        int permission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    private void checkPermission() {
        boolean hasPermission = hasStoragePermission();
        if (!hasPermission)
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE);
    }


    private void setupBottomNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setItemIconTintList(null);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    private void setupAppbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.songFragment, R.id.settingFragment)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.i(TAG, "onSupportNavigateUp: ");
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        if (navController.getCurrentDestination().getId() != R.id.fragmentNowPlaying2) {
            Log.i(TAG, "onSupportNavigateUp: 111111");
        } else {
            activityMainBinding.containerMain.containerControlBottom.setVisibility(View.VISIBLE);
        }
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Log.i(TAG, "onContextItemSelected: ");
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected: ");
        return super.onOptionsItemSelected(item);
    }

    public void eventDetailPlayingSong() {
        findViewById(R.id.containerControlBottom).setOnClickListener(v -> {
//            Navigation.findNavController(this,R.id.nav_host_fragment_content_main).navigate(R.id.action_global_fragmentNowPlaying2);
            MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.fragmentNowPlaying2);
            NavigationUI.onNavDestinationSelected(menuItem, navController);
            v.setVisibility(View.GONE);
        });
    }


    public void initSeekbar() {
        mainViewModel.getSongModelLiveData().observe(activityMainBinding.getLifecycleOwner(), songModel -> {
            activityMainBinding.containerMain.seekbarBottomControl.setMax((int) songModel.getDurationSong());
        });

        mainViewModel.getCurrentDurationLiveData().observe(activityMainBinding.getLifecycleOwner(), value -> {
            if (value != null)
                activityMainBinding.containerMain.seekbarBottomControl.setProgress(Math.toIntExact(value));
        });
    }

    public void updateSeekbar() {
        activityMainBinding.containerMain.seekbarBottomControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (mainViewModel.getSongModelLiveData() != null)
                        mainViewModel.updateSeekbar(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}