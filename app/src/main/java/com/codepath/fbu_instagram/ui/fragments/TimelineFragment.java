package com.codepath.fbu_instagram.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.viewmodels.TimelineViewModel;

public class TimelineFragment extends Fragment {
    private String TAG = "TimelineFragment";
    private TimelineViewModel timelineViewModel;

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
        // Inflate the layout for this fragment
        timelineViewModel = new TimelineViewModel(getContext());
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        timelineViewModel.getPostData();
        return view;
    }
}