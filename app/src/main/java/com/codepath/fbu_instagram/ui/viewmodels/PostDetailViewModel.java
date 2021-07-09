package com.codepath.fbu_instagram.ui.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.models.PostParcel;
import com.parse.ParseException;

import java.io.File;

public class PostDetailViewModel {
    private static final String TAG = "PostDetailViewModel";
    private Context context;
    private TextView tvUsername;
    private TextView tvDescription;
    private TextView tvLikes;
    private TextView tvTimeSincePosted;
    private ImageView ivImage;
    private ImageView ivAvatar;
    private PostParcel postParcel;

    public PostDetailViewModel(View view, PostParcel postParcel, TextView tvUsername, ImageView ivImage, TextView tvDescription, ImageView ivAvatar, TextView tvLikes, TextView tvTimeSincePosted) throws ParseException {
        tvUsername = view.findViewById(R.id.tvUsername);
        ivImage = view.findViewById(R.id.ivPost);
        tvDescription = view.findViewById(R.id.tvDescription);
        ivAvatar = view.findViewById(R.id.ivAvatar);
        tvLikes = view.findViewById(R.id.tvLikes);
        tvTimeSincePosted = view.findViewById(R.id.tvTimeSincePosted);
        tvUsername.setText(postParcel.user.getUsername());
        Bitmap takenImage = BitmapFactory.decodeFile(postParcel.image.getFile().getAbsolutePath());
        ivImage.setImageBitmap(takenImage);
        tvDescription.setText(postParcel.description);
    }
}
