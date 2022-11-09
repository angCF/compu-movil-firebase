package com.puj.taller03_cm.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.puj.taller03_cm.App;
import com.puj.taller03_cm.R;

public class AuthenticatedActivity extends BasicActivity {

    protected FirebaseUser currentUser;

    private Boolean isSelected=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((App) getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.signOutMenuButton:
                FirebaseAuth.getInstance().signOut();
                currentUser = null;
                startActivity(AuthenticationActivity.createIntent(this));
                break;
            case R.id.status:
                if(!isSelected){
                    item.setIcon(R.drawable.ic_baseline_online_24);
                    isSelected=true;
                    break;
                }else{
                    item.setIcon(R.drawable.ic_baseline_online_prediction_24);
                    isSelected=false;
                    break;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    protected boolean isAuthenticated() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isAuthenticated()) {
            startActivity(AuthenticationActivity.createIntent(this));
        } else {
            currentUser = FirebaseAuth.getInstance().getCurrentUser();
        }
    }
}