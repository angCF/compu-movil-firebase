package com.puj.taller03_cm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.puj.taller03_cm.R;
import com.puj.taller03_cm.databinding.ActivityMainBinding;

public class MainActivity extends AuthenticatedActivity {
    public static final String TAG = MainActivity.class.getName();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}