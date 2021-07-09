package com.codepath.fbu_instagram.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.domain.TakePhoto;
import com.codepath.fbu_instagram.ui.fragments.ComposeFragment;
import com.codepath.fbu_instagram.ui.fragments.ProfileFragment;
import com.codepath.fbu_instagram.ui.fragments.TimelineFragment;
import com.codepath.fbu_instagram.ui.viewmodels.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private final Fragment composeFragment = new ComposeFragment();
    private final Fragment profileFragment = new ProfileFragment();
    private final Fragment timelineFragment = new TimelineFragment();
    private MainViewModel mainViewModel;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        mainViewModel = new MainViewModel(MainActivity.this, this);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_profile:
                    fragment = profileFragment;
                    break;
                case R.id.action_compose:
                    fragment = composeFragment;
                    break;
                case R.id.action_home:
                default:
                    fragment = timelineFragment;
                    break;
            }
            fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
            return true;
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        composeFragment.onActivityResult(requestCode, resultCode, data);
    }
}