package com.codepath.fbu_instagram.ui.domain;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.fbu_instagram.UseCase;
import com.codepath.fbu_instagram.models.Post;
import com.codepath.fbu_instagram.models.PostParcel;

public class LikePost extends UseCase {
    private static final String TAG = "LikePost";
    private Context context;
    private TextView likeLabel;
    private Post post;
    private ImageView heart;

    public LikePost(Context context, TextView likeLabel, Post post, ImageView heart) {
        this.context = context;
        this.likeLabel = likeLabel;
        this.post = post;
        this.heart = heart;
    }

    @Override
    public void executeUseCase() {
        submitLike();
    }

    public void submitLike() {
        heart.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                heart.setVisibility(View.INVISIBLE);
            }
        }, 200);

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
