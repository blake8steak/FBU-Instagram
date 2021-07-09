package com.codepath.fbu_instagram.ui.domain;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.UseCase;
import com.codepath.fbu_instagram.models.Post;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

public class SubmitPost extends UseCase {
    private final String TAG = "SubmitLogin";
    private Context context;
    private BottomNavigationView bottomNavigationView;
    private EditText descTextField;
    private ImageView ivPreviewImage;

    private String description;
    private File photoFile;

    public SubmitPost(Context context, BottomNavigationView bottomNavigationView, EditText descTextField, ImageView ivPreviewImage) {
        this.context = context;
        this.bottomNavigationView = bottomNavigationView;
        this.descTextField = descTextField;
        this.ivPreviewImage = ivPreviewImage;
    }

    @Override
    public void executeUseCase() {
        submitPost();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoFile(File photoFile) {
        this.photoFile = photoFile;
    }

    public void submitPost() {
        Post post = new Post();
        post.setUser(ParseUser.getCurrentUser());
        if(description.length() == 0) {
            Log.e(TAG, "No description on post.");
            Toast.makeText(context, "Please enter a description.", Toast.LENGTH_SHORT);
        } else if (photoFile == null) {
            Toast.makeText(context, "Please take a photo.", Toast.LENGTH_SHORT);
            Log.e(TAG, "No photo on post.");
        } else {
            post.setDescription(description);
            post.setImage(new ParseFile(photoFile));
            post.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e != null) {
                        Log.e(TAG, "Error submitting post: "+e.getCode()+", "+e.getMessage());
                    }
                    Log.i(TAG, "Post successful.");
                }
            });
            descTextField.setText("");
            ivPreviewImage.setImageResource(0);
            bottomNavigationView.setSelectedItemId(R.id.action_home);
        }
    }
}
