package com.codepath.fbu_instagram.ui.domain;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.codepath.fbu_instagram.UseCase;
import com.codepath.fbu_instagram.models.Post;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

public class SubmitPost extends UseCase {
    private final String TAG = "SubmitLogin";
    private Context context;

    private String description;
    private File photoFile;

    public SubmitPost(Context context) {
        this.context = context;
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
        post.setDescription(description);
        post.setUser(ParseUser.getCurrentUser());
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
    }
}
