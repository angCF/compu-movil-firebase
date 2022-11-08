package com.puj.taller03_cm.services;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.puj.taller03_cm.activities.BasicActivity;
import com.puj.taller03_cm.utils.PermissionHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lombok.Getter;

public class CameraService {
    public static final String TAG = CameraService.class.getName();
    @Getter
    private String photoName;
    @Getter
    private Uri photoURI;

    public void startCamera(BasicActivity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        // Create the File where the photo should go
        File photoFile = null;
        try {
            photoFile = createImageFile(activity);
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }
        // Continue only if the File was successfully created
        if (photoFile != null) {
            photoURI = FileProvider.getUriForFile(activity,
                    "com.example.android.fileprovider",
                    photoFile);
            photoName = photoFile.getName();
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            activity.startActivityForResult(takePictureIntent, PermissionHelper.PERMISSIONS_REQUEST_CAMERA);
        }
    }

    private File createImageFile(BasicActivity activity) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "CAMARA_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        String currentPhotoPath = image.getAbsolutePath();
        Log.i(TAG, String.format("Path: %s", currentPhotoPath));
        return image;
    }

    public void startGallery(BasicActivity activity) {
        Intent pickGalleryImage = new Intent(Intent.ACTION_PICK);
        pickGalleryImage.setType("image/*");
        activity.startActivityForResult(pickGalleryImage, PermissionHelper.PERMISSIONS_REQUEST_GALLERY);
    }
}
