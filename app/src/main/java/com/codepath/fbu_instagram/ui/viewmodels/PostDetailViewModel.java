package com.codepath.fbu_instagram.ui.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.models.Post;
import com.codepath.fbu_instagram.models.PostParcel;
import com.parse.ParseException;

import java.io.File;
import java.util.Date;

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

    public PostDetailViewModel(View view, PostParcel postParcel) throws ParseException {
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
        tvTimeSincePosted.setText(calculateTimeAgo(postParcel.postDate));
    }

    public static String calculateTimeAgo(Date createdAt) {

        int SECOND_MILLIS = 1000;
        int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        int DAY_MILLIS = 24 * HOUR_MILLIS;

        try {
            createdAt.getTime();
            long time = createdAt.getTime();
            long now = System.currentTimeMillis();

            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return "0s";
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "1m";
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + "m";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "1h";
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + "h";
            } else if (diff < 48 * HOUR_MILLIS) {
                return "1d";
            } else {
                return diff / DAY_MILLIS + "d";
            }
        } catch (Exception e) {
            Log.i("Error:", "getRelativeTimeAgo failed", e);
            e.printStackTrace();
        }

        return "";
    }
}
