package com.codepath.fbu_instagram.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.viewmodels.ProfileViewModel;
import com.parse.ParseException;
import com.parse.ParseUser;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private Button btnSignOut;
    private ImageView ivUserAvatar;
    private TextView tvRealName;
    private TextView tvScreenName;
    private ProfileViewModel profileViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileViewModel = new ProfileViewModel(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnSignOut = view.findViewById(R.id.btnSignOut);
        ivUserAvatar = view.findViewById(R.id.ivUserAvatar);
        tvScreenName = view.findViewById(R.id.tvScreenName);
        tvRealName = view.findViewById(R.id.tvRealName);
        try {
            Bitmap takenImage = BitmapFactory.decodeFile(ParseUser.getCurrentUser().getParseFile("userAvi").getFile().getAbsolutePath());
            ivUserAvatar.setImageBitmap(takenImage);
        } catch (ParseException e) {
            Log.e(TAG, "Could not load user avatar.");
        }
        tvScreenName.setText("@"+ParseUser.getCurrentUser().getUsername());
        tvRealName.setText(ParseUser.getCurrentUser().getString("name"));
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileViewModel.signOut();
            }
        });
        return view;
    }
}