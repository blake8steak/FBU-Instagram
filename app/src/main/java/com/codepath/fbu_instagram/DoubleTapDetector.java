package com.codepath.fbu_instagram;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import com.codepath.fbu_instagram.models.Post;
import com.codepath.fbu_instagram.ui.domain.LikePost;

public class DoubleTapDetector extends GestureDetector.SimpleOnGestureListener {
    private final String TAG = "DoubleTapDetector";
    private LikePost likePost;
    private Post post;
    private Context context;
    private TextView likeLabel;

    public DoubleTapDetector(Post post, Context context, TextView likeLabel) {
        this.post = post;
        this.context = context;
        this.likeLabel = likeLabel;
    }


    @Override
    public boolean onDoubleTap(MotionEvent e)
    {
        likePost = new LikePost(context, likeLabel, post);
        likePost.executeUseCase();
        return true;
    }
}
