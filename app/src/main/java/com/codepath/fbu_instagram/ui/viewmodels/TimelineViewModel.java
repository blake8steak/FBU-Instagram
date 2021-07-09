package com.codepath.fbu_instagram.ui.viewmodels;


import android.content.Context;
import android.util.Log;


import androidx.appcompat.app.ActionBar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.codepath.fbu_instagram.adapters.PostsAdapter;
import com.codepath.fbu_instagram.models.Post;
import com.codepath.fbu_instagram.ui.domain.GetPosts;
import com.codepath.fbu_instagram.ui.domain.LikePost;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class TimelineViewModel {
    private final String TAG = "TimelineViewModel";
    private Context viewContext;

    private GetPosts getPosts;
    List<Post> allPosts;
    PostsAdapter adapter;
    SwipeRefreshLayout swipeContainer;
    ActionBar actionBar;

    public TimelineViewModel(Context context, ActionBar actionBar, List<Post> allPosts, PostsAdapter adapter, SwipeRefreshLayout swipeContainer) {
        this.viewContext = context;
        this.allPosts = allPosts;
        this.adapter = adapter;
        this.swipeContainer = swipeContainer;
        this.actionBar = actionBar;
        getPosts = new GetPosts(viewContext, actionBar, allPosts, adapter);
    }

    public void getPostData() {
        actionBar.setTitle("Loading...");
        getPosts.executeUseCase();
        //actionBar.setTitle("Instagram");
    }

    public void fetchTimelineAsync(int page) {
        swipeContainer.setRefreshing(true);
        adapter.clear();
        getPostData();
        swipeContainer.setRefreshing(false);
    }

    //endless scrolling
    public void onLoadMore() {
        Log.i(TAG, "running onLoadMore");
        getPosts.executeUseCase();
    }
}
