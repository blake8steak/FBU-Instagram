package com.codepath.fbu_instagram.ui.domain;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.codepath.fbu_instagram.UseCase;
import com.codepath.fbu_instagram.adapters.PostsAdapter;
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
    List<Post> allPosts;
    PostsAdapter adapter;
    ActionBar actionBar;

    public GetPosts(Context context, ActionBar actionBar, List<Post> allPosts, PostsAdapter adapter) {
        this.context = context;
        this.allPosts = allPosts;
        this.adapter = adapter;
        this.actionBar = actionBar;
    }

    @Override
    public void executeUseCase() {
        queryPosts();
    }

    public void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.setSkip(allPosts.size());
        Log.i(TAG, "size of all posts: "+allPosts.size());
        query.addDescendingOrder("createdAt");
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e == null) {
                    for(Post p : objects) {
                        Log.i(TAG, "Post from user "+p.getUser().getUsername()+": "+p.getDescription());
                    }
                    // save received posts to list and notify adapter of new data
                    allPosts.addAll(objects);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                actionBar.setTitle("Instagram");
            }
        });
    }
}
