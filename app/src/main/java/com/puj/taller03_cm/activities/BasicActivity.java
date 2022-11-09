package com.puj.taller03_cm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.puj.taller03_cm.App;
import com.puj.taller03_cm.services.CameraService;
import com.puj.taller03_cm.services.LocationService;
import com.puj.taller03_cm.utils.AlertsHelper;
import com.puj.taller03_cm.utils.PermissionHelper;

import javax.inject.Inject;

public class BasicActivity extends AppCompatActivity {
    @Inject
    AlertsHelper alertsHelper;
    @Inject
    CameraService cameraService;
    @Inject
    PermissionHelper permissionHelper;
    @Inject
    LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((App) getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }
}