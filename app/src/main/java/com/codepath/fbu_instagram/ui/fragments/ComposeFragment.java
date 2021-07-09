package com.codepath.fbu_instagram.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.MainActivity;
import com.codepath.fbu_instagram.ui.domain.TakePhoto;
import com.codepath.fbu_instagram.ui.viewmodels.ComposeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ComposeFragment extends Fragment {
    private static final int RESULT_OK = -1;
    private final String TAG = "ComposeFragment";
    private ComposeViewModel composeViewModel;
    private EditText etDescription;
    private ImageView ivPreviewImage;
    private Button btnPost;
    private Button btnTakePhoto;
    private BottomNavigationView bottomNavigationView;

    public ComposeFragment() {
        // Required empty public constructor
    }

    public ComposeFragment(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compose, container, false);
        ivPreviewImage = view.findViewById(R.id.ivPreviewImage);
        etDescription = view.findViewById(R.id.etDescription);
        btnTakePhoto = view.findViewById(R.id.btnTakePhoto);
        btnPost = view.findViewById(R.id.btnPost);
        composeViewModel = new ComposeViewModel(getContext(), bottomNavigationView, this, etDescription, ivPreviewImage, getActivity());

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeViewModel.takePhoto();
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeViewModel.submitPost(etDescription.getText().toString());

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TakePhoto.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                composeViewModel.savePhoto();
            }
        }
    }
}