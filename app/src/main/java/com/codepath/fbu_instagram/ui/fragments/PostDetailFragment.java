package com.codepath.fbu_instagram.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.models.Post;
import com.codepath.fbu_instagram.models.PostParcel;
import com.codepath.fbu_instagram.ui.viewmodels.PostDetailViewModel;
import com.parse.ParseException;

import org.parceler.Parcels;

public class PostDetailFragment extends Fragment {
    private static final String TAG = "PostDetailFragment";
    private TextView tvUsername;
    private TextView tvDescription;
    private TextView tvLikes;
    private TextView tvTimeSincePosted;
    private ImageView ivImage;
    private ImageView ivAvatar;
    private PostParcel postParcel;
    private PostDetailViewModel postDetailViewModel;

    public PostDetailFragment() {
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
        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);
        tvUsername = view.findViewById(R.id.tvUsername);
        ivImage = view.findViewById(R.id.ivPost);
        tvDescription = view.findViewById(R.id.tvDescription);
        ivAvatar = view.findViewById(R.id.ivAvatar);
        tvLikes = view.findViewById(R.id.tvLikes);
        tvTimeSincePosted = view.findViewById(R.id.tvTimeSincePosted);
        Bundle bundle = getArguments();
        if(bundle != null) {
            postParcel = Parcels.unwrap(bundle.getParcelable(PostParcel.class.getSimpleName()));
            Log.i(TAG, postParcel.user.getUsername()+": "+postParcel.description);
            try {
                postDetailViewModel = new PostDetailViewModel(view, postParcel, tvUsername, ivImage, tvDescription, ivAvatar, tvLikes, tvTimeSincePosted);
            } catch(ParseException e) {
                Log.e(TAG, "Error loading image into detail view");
            }
        }
        return view;
    }
}