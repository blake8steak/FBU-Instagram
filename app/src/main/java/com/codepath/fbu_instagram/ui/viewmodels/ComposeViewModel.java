package com.codepath.fbu_instagram.ui.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.widget.EditText;
import android.widget.ImageView;

import com.codepath.fbu_instagram.ui.domain.SubmitPost;
import com.codepath.fbu_instagram.ui.domain.TakePhoto;

public class ComposeViewModel {
    private final String TAG = "ComposeViewModel";
    private Context viewContext;
    private Activity mainActivity;
    private EditText descTextField;
    private ImageView ivPreviewImage;

    private SubmitPost submitPost;
    private TakePhoto takePhoto;

    public ComposeViewModel(Context context, EditText descTextField, ImageView ivPreviewImage, Activity activity) {
        this.viewContext = context;
        this.descTextField = descTextField;
        this.mainActivity = activity;
        this.ivPreviewImage = ivPreviewImage;
        submitPost = new SubmitPost(viewContext);
        takePhoto = new TakePhoto(viewContext, mainActivity, ivPreviewImage);
    }

    public void submitPost(String description) {
        submitPost.setParams(description);
        descTextField.setText("");
        submitPost.executeUseCase();
    }

    public void takePhoto() {
        takePhoto.executeUseCase();
    }

    public void savePhoto() {
        takePhoto.savePhoto();
    }
}
