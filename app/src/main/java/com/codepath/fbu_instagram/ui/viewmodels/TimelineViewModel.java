package com.codepath.fbu_instagram.ui.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.codepath.fbu_instagram.adapters.PostsAdapter;
import com.codepath.fbu_instagram.models.Post;
import com.codepath.fbu_instagram.ui.domain.GetPosts;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.List;

public class TimelineViewModel {
    private final String TAG = "TimelineViewModel";
    private Context viewContext;

    private GetPosts getPosts;
    List<Post> allPosts;
    PostsAdapter adapter;
    SwipeRefreshLayout swipeContainer;

    public TimelineViewModel(Context context, List<Post> allPosts, PostsAdapter adapter, SwipeRefreshLayout swipeContainer) {
        this.viewContext = context;
        this.allPosts = allPosts;
        this.adapter = adapter;
        this.swipeContainer = swipeContainer;
        getPosts = new GetPosts(viewContext, allPosts, adapter);
    }

    public void getPostData() {
        getPosts.executeUseCase();
    }

    public void fetchTimelineAsync(int page) {
        swipeContainer.setRefreshing(true);
        adapter.clear();
        getPostData();
        swipeContainer.setRefreshing(false);
    }

}
