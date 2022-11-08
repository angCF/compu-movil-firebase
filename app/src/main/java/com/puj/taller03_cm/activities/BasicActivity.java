package com.puj.taller03_cm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.puj.taller03_cm.App;

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((App) getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }
}