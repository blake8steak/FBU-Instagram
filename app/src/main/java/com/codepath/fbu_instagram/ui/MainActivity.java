package com.codepath.fbu_instagram.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.fragments.ComposeFragment;
import com.codepath.fbu_instagram.ui.fragments.ProfileFragment;
import com.codepath.fbu_instagram.ui.fragments.TimelineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    final FragmentManager fragmentManager = getSupportFragmentManager();
    final Fragment composeFragment = new ComposeFragment();
    final Fragment profileFragment = new ProfileFragment();
    final Fragment timelineFragment = new TimelineFragment();

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

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
}