package com.codepath.fbu_instagram.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.domain.TakePhoto;
import com.codepath.fbu_instagram.ui.viewmodels.ComposeViewModel;

public class ComposeFragment extends Fragment {
    private final String TAG = "ComposeFragment";
    private ComposeViewModel composeViewModel;
    private EditText etDescription;
    private ImageView ivPreviewImage;
    private Button btnPost;
    private Button btnTakePhoto;

    public ComposeFragment() {
        // Required empty public constructor
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
        composeViewModel = new ComposeViewModel(getContext(), etDescription, ivPreviewImage, getActivity());

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
        composeViewModel.savePhoto();
    }
}