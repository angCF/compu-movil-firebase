package com.puj.taller03_cm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.puj.taller03_cm.R;
import com.puj.taller03_cm.services.LocationService;
import com.puj.taller03_cm.databinding.ActivityMainBinding;

public class MainActivity extends AuthenticatedActivity {
    public static final String TAG = MainActivity.class.getName();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        locationService.setLocationCallback(new LocationCallback() {
            @Override
            public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }

            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.i(TAG, "onLocationResult: " + locationResult.getLastLocation().toString());
                MapFragment fragment = binding.fragmentContainerView.getFragment();
                fragment.updateUserPositionOnMap(locationResult);
            }
        });
    }
}