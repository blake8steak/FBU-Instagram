package com.codepath.fbu_instagram.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.adapters.PostsAdapter;
import com.codepath.fbu_instagram.models.Post;
import com.codepath.fbu_instagram.ui.viewmodels.TimelineViewModel;

import java.util.ArrayList;
import java.util.List;

public class TimelineFragment extends Fragment {
    private String TAG = "TimelineFragment";
    private TimelineViewModel timelineViewModel;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;

    public TimelineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        swipeContainer = view.findViewById(R.id.swipeContainer);
        timelineViewModel = new TimelineViewModel(getContext(), allPosts, adapter, swipeContainer);
        rvPosts = view.findViewById(R.id.rvPosts);
        // set the adapter on the recycler view
        rvPosts.setAdapter(adapter);
        // set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        timelineViewModel.getPostData();


        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                timelineViewModel.fetchTimelineAsync(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright);

        return view;
    }
}