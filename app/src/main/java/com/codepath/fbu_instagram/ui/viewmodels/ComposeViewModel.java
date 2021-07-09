package com.codepath.fbu_instagram.ui.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.MainActivity;
import com.codepath.fbu_instagram.ui.domain.SubmitPost;
import com.codepath.fbu_instagram.ui.domain.TakePhoto;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class ComposeViewModel {
    private final String TAG = "ComposeViewModel";
    private Context viewContext;
    private Activity mainActivity;
    private EditText descTextField;
    private ImageView ivPreviewImage;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;

    private SubmitPost submitPost;
    private TakePhoto takePhoto;

    public ComposeViewModel(Context context, BottomNavigationView bottomNavigationView, Fragment fragment, EditText descTextField, ImageView ivPreviewImage, Activity activity) {
        this.viewContext = context;
        this.descTextField = descTextField;
        this.mainActivity = activity;
        this.ivPreviewImage = ivPreviewImage;
        this.fragment = fragment;
        this.bottomNavigationView = bottomNavigationView;
        submitPost = new SubmitPost(viewContext, bottomNavigationView, descTextField, ivPreviewImage);
        takePhoto = new TakePhoto(viewContext, mainActivity, ivPreviewImage, submitPost);
    }

    public void submitPost(String description) {
        //photoFile is set via takePhoto
        submitPost.setDescription(description);
        submitPost.executeUseCase();
    }

    public void takePhoto() {
        takePhoto.executeUseCase();
    }

    public void savePhoto() {
        takePhoto.savePhoto();
    }
}
