package com.codepath.fbu_instagram.ui.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.ui.domain.TakePhoto;

import java.io.File;

public class MainViewModel {
    private final String TAG = "MainActivity";
    private Context viewContext;
    private Activity activity;

    //used for onActivityResult

    public MainViewModel(Context context, Activity activity) {
        this.viewContext = context;
        this.activity = activity;
    }
}
