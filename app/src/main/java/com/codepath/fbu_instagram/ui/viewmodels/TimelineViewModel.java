package com.codepath.fbu_instagram.ui.viewmodels;

import android.content.Context;

import com.codepath.fbu_instagram.adapters.PostsAdapter;
import com.codepath.fbu_instagram.models.Post;
import com.codepath.fbu_instagram.ui.domain.GetPosts;

import java.util.List;

public class TimelineViewModel {
    private final String TAG = "TimelineViewModel";
    private Context viewContext;

    private GetPosts getPosts;

    public TimelineViewModel(Context context, List<Post> allPosts, PostsAdapter adapter) {
        this.viewContext = context;
        getPosts = new GetPosts(viewContext, allPosts, adapter);
    }

    public void getPostData() {
        getPosts.executeUseCase();
    }


}
