package com.codepath.fbu_instagram.ui.viewmodels;

import android.content.Context;

import com.codepath.fbu_instagram.ui.domain.GetPosts;

public class TimelineViewModel {
    private final String TAG = "TimelineViewModel";
    private Context viewContext;

    private GetPosts getPosts;

    public TimelineViewModel(Context context) {
        this.viewContext = context;
        getPosts = new GetPosts(viewContext);
    }

    public void getPostData() {
        getPosts.executeUseCase();
    }


}
