package com.codepath.fbu_instagram.ui.domain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;

import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.UseCase;
import com.codepath.fbu_instagram.ui.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class TakePhoto extends UseCase {
    private final String TAG = "TakePhoto";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;

    private Context context;
    private Activity activity;
    private String photoFileName;
    private File photoFile;
    private ImageView ivPreviewImage;
    private SubmitPost submitPost;

    public TakePhoto(Context context, Activity activity, ImageView ivPreviewImage) {
        this.context = context;
        this.activity = activity;
        this.ivPreviewImage = ivPreviewImage;
        photoFileName = "photo.jpg";
    }

    public TakePhoto(Context context, Activity activity, ImageView ivPreviewImage, SubmitPost submitPost) {
        this.context = context;
        this.activity = activity;
        this.ivPreviewImage = ivPreviewImage;
        this.photoFileName = "photo.jpg";
        this.submitPost = submitPost;
    }

    @Override
    public void executeUseCase() {
        takePhoto();
    }

    public void savePhoto() {
        Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
        takenImage = rotateBitmapOrientation(photoFile.getAbsolutePath());
        ivPreviewImage.setImageBitmap(takenImage);
        if(submitPost != null) {
            submitPost.setPhotoFile(photoFile);
        }
    }

    public void takePhoto() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        Uri fileProvider = FileProvider.getUriForFile(context, "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If result is not null, it's safe to use intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            // Start image capture intent to take photo
            activity.startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    // Returns File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }

    public static Bitmap rotateBitmapOrientation(String photoFilePath) {
        // Create and configure BitmapFactory
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoFilePath, bounds);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile(photoFilePath, opts);
        // Read EXIF Data
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(photoFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
        // Return result
        return rotatedBitmap;
    }
}
