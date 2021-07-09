package com.codepath.fbu_instagram.ui.domain;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.codepath.fbu_instagram.UseCase;
import com.codepath.fbu_instagram.models.Post;
import com.codepath.fbu_instagram.models.PostParcel;

public class LikePost extends UseCase {
    private static final String TAG = "LikePost";
    private Context context;
    private TextView likeLabel;
    private Post post;

    public LikePost(Context context, TextView likeLabel, Post post) {
        this.context = context;
        this.likeLabel = likeLabel;
        this.post = post;
    }

    @Override
    public void executeUseCase() {
        submitLike();
    }

    public void submitLike() {
        int numLikes = post.getInt("likes") + 1;
        post.put("likes", numLikes);
        post.saveInBackground(e1 -> {
            if (e1 == null) {
                Log.i(TAG, "Success! Post was liked");
            } else {
                Log.e(TAG, e1.getMessage());
            }
        });
        likeLabel.setText(numLikes+" likes");
    }
}
