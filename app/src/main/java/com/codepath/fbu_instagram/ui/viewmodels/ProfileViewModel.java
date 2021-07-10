package com.codepath.fbu_instagram.ui.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.domain.SignOut;
import com.parse.ParseException;
import com.parse.ParseUser;

public class ProfileViewModel {
    private final String TAG = "ProfileViewModel";
    private Context viewContext;
    private View view;
    private SignOut signOut;
    private ImageView ivUserAvatar;
    private TextView tvRealName;
    private TextView tvScreenName;
    private ProfileViewModel profileViewModel;

    public ProfileViewModel(Context context, View view) {
        this.viewContext = context;
        this.view = view;
        signOut = new SignOut(viewContext);
    }

    public void signOut() {
        signOut.executeUseCase();
    }

    public void displayUserData() {
        ivUserAvatar = view.findViewById(R.id.ivUserAvatar);
        tvScreenName = view.findViewById(R.id.tvScreenName);
        tvRealName = view.findViewById(R.id.tvRealName);
        tvScreenName.setText("@"+ParseUser.getCurrentUser().getUsername());
        tvRealName.setText(ParseUser.getCurrentUser().getString("name"));
        try {
            Bitmap takenImage = BitmapFactory.decodeFile(ParseUser.getCurrentUser().getParseFile("userAvi").getFile().getAbsolutePath());
            ivUserAvatar.setImageBitmap(takenImage);
        } catch (ParseException e) {
            Log.e(TAG, "Could not load user avatar.");
        }
    }
}
