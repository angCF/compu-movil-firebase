package com.puj.taller03_cm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.github.javafaker.Faker;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.puj.taller03_cm.R;
import com.puj.taller03_cm.databinding.ActivityCreateUserBinding;
import com.puj.taller03_cm.models.DatabaseRoutes;
import com.puj.taller03_cm.models.UserInfo;
import com.puj.taller03_cm.services.LocationService;

import java.util.Date;
import java.util.Objects;

public class CreateUserActivity extends BasicActivity {
    public static final String TAG = AuthenticationActivity.class.getName();
    ActivityCreateUserBinding binding;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        binding.createButton.setOnClickListener(view -> doSignup());
    }

    private void doSignup() {
//        String email = binding.createEmail.getEditText().getText().toString();
//        String pass = binding.createPass.getEditText().getText().toString();
        String email = Objects.requireNonNull(binding.createEmail.getEditText()).getText().toString();
        String pass = Objects.requireNonNull(binding.createPass.getEditText()).getText().toString();
//        LocationManager locationManager = (LocationManager)
//                getSystemService(Context.LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = locationManager.getLastKnownLocation(locationManager
//                .getBestProvider(criteria, false));
//        double latitude = location.getLatitude();
//        double longitud = location.getLongitude();

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

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener(authResult -> {
                    DatabaseReference reference = mDatabase.getReference(DatabaseRoutes.getUser(authResult.getUser().getUid()));
                    UserInfo tmpUser = new UserInfo(
                            binding.createDisplayName.getEditText().getText().toString().isEmpty() ? Faker.instance().funnyName().name() : binding.createDisplayName.getEditText().getText().toString(),
                            binding.createDisplaySurname.getEditText().getText().toString().isEmpty() ? Faker.instance().funnyName().name() : binding.createDisplaySurname.getEditText().getText().toString(),
                            binding.createEmail.getEditText().getText().toString(),
                            binding.createPass.getEditText().getText().toString(),
                            Long.parseLong(binding.numberid.getEditText().getText().toString().isEmpty()? Faker.instance().phoneNumber().cellPhone().replace("-","") : binding.numberid.getEditText().getText().toString()),
                            Double.parseDouble(binding.latitud.getEditText().getText().toString().isEmpty() ? String.valueOf(Double.parseDouble(binding.latitud.getEditText().getText().toString())) : binding.latitud.getEditText().getText().toString()),
                            Double.parseDouble(binding.latitud.getEditText().getText().toString().isEmpty() ? String.valueOf(Double.parseDouble(binding.longitud.getEditText().getText().toString())) : binding.longitud.getEditText().getText().toString())
//                            Double.parseDouble(binding.latitud.getEditText().getText().toString().isEmpty() ? String.valueOf(latitude) : binding.latitud.getEditText().getText().toString()),
//                            Double.parseDouble(binding.longitud.getEditText().getText().toString().isEmpty() ? String.valueOf(longitud) : binding.longitud.getEditText().getText().toString())
                            //La imagen
                            );
//                    UserInfo tmpUser = new UserInfo(
//                            binding.createDisplayName.getEditText().getText().toString().isEmpty() ? Faker.instance().funnyName().name() : binding.createDisplayName.getEditText().getText().toString(),
//                            binding.createEmail.getEditText().getText().toString(),
//                            Long.parseLong(binding.createPhone.getEditText().getText().toString().isEmpty()? Faker.instance().phoneNumber().cellPhone().replace("-","") : binding.createPhone.getEditText().getText().toString()),
//                            new Date().getTime(),
//                            new Date().getTime());
                    reference.setValue(tmpUser).addOnSuccessListener(unused ->
                            //La otra actividad
                                    startActivity(new Intent(CreateUserActivity.this, MainActivity.class)))
                            .addOnFailureListener(e ->
                                    alertsHelper.shortSimpleSnackbar(binding.getRoot(), e.getLocalizedMessage()));
                })
                .addOnFailureListener(e ->
                        alertsHelper.shortSimpleSnackbar(binding.getRoot(), e.getLocalizedMessage()));
    }
}