package com.codepath.fbu_instagram.ui.domain;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.codepath.fbu_instagram.UseCase;
import com.codepath.fbu_instagram.models.Post;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.List;

public class GetPosts extends UseCase {
    private final String TAG = "GetPosts";
    private Context context;

    public GetPosts(Context context) {
        this.context = context;
    }

    @Override
    public void executeUseCase() {
        queryPosts();
    }

    public void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        // Specify the object id
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e == null) {
                    for(Post p : objects) {
                        Log.i(TAG, "Post from user "+p.getUser().getUsername()+": "+p.getDescription());
                    }
                }
            }
        });
    }
}
