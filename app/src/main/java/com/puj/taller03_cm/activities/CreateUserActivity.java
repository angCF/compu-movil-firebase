package com.puj.taller03_cm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.github.javafaker.Faker;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.puj.taller03_cm.R;
import com.puj.taller03_cm.databinding.ActivityCreateUserBinding;
import com.puj.taller03_cm.models.DatabaseRoutes;
import com.puj.taller03_cm.models.UserInfo;
import com.puj.taller03_cm.services.LocationService;
import com.puj.taller03_cm.utils.AlertUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CreateUserActivity extends BasicActivity {
    public static final String TAG = AuthenticationActivity.class.getName();
    ActivityCreateUserBinding binding;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;

    private Uri photoURI = null;
    String namePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage.getInstance();

        binding.createButton.setOnClickListener(view -> doSignup());
        binding.cameraButton.setOnClickListener( view -> {
            permissionHelper.getCameraPermission(this);
            if (permissionHelper.isMCameraPermissionGranted()){
                cameraService.startCamera(this);
            }
        });

        // binding.galeriaButton.setOnClickListener(view -> startGallery(binding.getRoot()));

    }

    private void doSignup() {
        String email = Objects.requireNonNull(binding.createEmail.getEditText()).getText().toString();
        String pass = Objects.requireNonNull(binding.createPass.getEditText()).getText().toString();
        String name = Objects.requireNonNull(binding.createDisplayName.getEditText()).getText().toString();
        String lastName = Objects.requireNonNull(binding.createDisplaySurname.getEditText()).getText().toString();
        Long identification = Long.valueOf(Objects.requireNonNull(binding.numberid.getEditText()).getText().toString());
        Double latitud = Double.valueOf(Objects.requireNonNull(binding.latitud.getEditText()).getText().toString());
        Double longitud = Double.valueOf(Objects.requireNonNull(binding.longitud.getEditText()).getText().toString());

        if (email.isEmpty()) {
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), getString(R.string.mail_error_label));
            binding.createEmail.setErrorEnabled(true);
            binding.createEmail.setError(getString(R.string.mail_error_label));
            return;
        }

        if (pass.isEmpty()) {
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), getString(R.string.error_pass_label));
            binding.createPass.setErrorEnabled(true);
            binding.createPass.setError(getString(R.string.error_pass_label));
            return;
        }

        if (photoURI == null) {
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), "Debe seleccionar una foto");
            return;
        }

        if (name.isEmpty()) {
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), getString(R.string.error_name_label));
            binding.createPass.setErrorEnabled(true);
            binding.createPass.setError(getString(R.string.error_name_label));
            return;
        }

        if (lastName.isEmpty()) {
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), getString(R.string.error_last_label));
            binding.createPass.setErrorEnabled(true);
            binding.createPass.setError(getString(R.string.error_last_label));
            return;
        }

        if (identification == null) {
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), getString(R.string.error_identification_label));
            binding.createPass.setErrorEnabled(true);
            binding.createPass.setError(getString(R.string.error_identification_label));
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(authResult -> {
            StorageReference mStorageReference = mStorage.getReference(DatabaseRoutes.getUser(photoURI.getLastPathSegment()));
            mStorageReference.putFile(photoURI).addOnSuccessListener(taskSnapshot -> {
                mStorageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    DatabaseReference reference = mDatabase.getReference(DatabaseRoutes.getUser(authResult.getUser().getUid()));
                    UserInfo tmpUser = new UserInfo(
                            authResult.getUser().getUid(),
                            name,
                            lastName,
                            email,
                            pass,
                            identification,
                            latitud,
                            longitud,
                            true,
                            uri.toString()
                    );

                    reference.setValue(tmpUser).addOnFailureListener(e -> {
                        alertsHelper.shortToast(getApplicationContext(), "error");
                        return;
                    });
                }).addOnFailureListener(e -> {
                    alertsHelper.shortToast(getApplicationContext(), e.getMessage());
                    return;
                });
            }).addOnFailureListener(e -> {
                alertsHelper.shortToast(getApplicationContext(), e.getMessage());
                return;
            });
        }).addOnSuccessListener(unused -> {
            alertsHelper.shortToast(getApplicationContext(), "Usuario creado correctamente");
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    private void requestPermission(Activity context, String permission, String justification, int id) {
        // Verificar si no hay permisos
        if (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_DENIED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                    Manifest.permission.CAMERA)) {
                AlertUtils.shortSimpleSnackbar(binding.getRoot(), justification);
            }
            // request the permission.
            ActivityCompat.requestPermissions(context, new String[]{permission}, id);
        }
    }

/*
    public void startCamera(View view){
        if (ContextCompat.checkSelfPermission(this, cameraPerm)
                == PackageManager.PERMISSION_GRANTED){
            openCamera();
        }else {
            AlertUtils.indefiniteSnackbar(binding.getRoot(), getString(R.string.permission_denied_label));
        }
    }
*/

//    private void openCamera() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                Log.e(TAG, ex.getMessage());
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                this.photoURI = FileProvider.getUriForFile(this,
//                        "com.example.android.fileprovider",
//                        photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, CAMERA_PERMISSION_ID);
//            }
//        }
//    }

//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "CAMARA_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        currentPhotoPath = image.getAbsolutePath();
//        Log.i(TAG, String.format("Ruta: %s", currentPhotoPath));
//        return image;
//    }
//
//    public void startGallery(View view){
//        Intent pickGalleryImage = new Intent(Intent.ACTION_PICK);
//        pickGalleryImage.setType("image/*");
//        startActivityForResult(pickGalleryImage, GALLERY_PERMISSION_ID);
//    }
}