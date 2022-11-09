package com.puj.taller03_cm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.puj.taller03_cm.databinding.ActivityAuthenticationBinding;

public class AuthenticationActivity extends BasicActivity {

    public static final String TAG = AuthenticationActivity.class.getName();
    ActivityAuthenticationBinding binding;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.loginButton.setOnClickListener(view -> doLogin());
//        binding.signupButton.setOnClickListener(view -> doSignUp());
        binding.signupButton.setOnClickListener(view -> startActivity(new Intent(this, CreateUserActivity.class)));
        binding.forgotButton.setOnClickListener(view -> forgotPwd());
    }

    public static Intent createIntent(Activity activity) {
        return new Intent(activity, AuthenticationActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    private void doLogin(){
        String email = binding.loginEmail.getEditText().getText().toString();
        String pwd = binding.loginPass.getEditText().getText().toString();

        if(email == null || email.isEmpty() || pwd == null || pwd.isEmpty()){
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), "Los campos no pueden estar vacios.");

            binding.loginEmail.setError("Email vacio");
            binding.loginEmail.setErrorEnabled(true);
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnSuccessListener(authResult -> {
                    Log.i(TAG, "doLogin: login success");
                    startActivity(new Intent(this, MainActivity.class));
                }).addOnFailureListener(e -> {
                    Log.e(TAG, "doLogin: " + e.toString());
                    alertsHelper.shortSimpleSnackbar(binding.getRoot(), e.getLocalizedMessage());
                });
    }

    private void doSignUp(){
        String email = binding.loginEmail.getEditText().getText().toString();
        String pwd = binding.loginPass.getEditText().getText().toString();

        if(email == null || email.isEmpty() || pwd == null || pwd.isEmpty()){
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), "Los campos no pueden estar vacios.");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnSuccessListener(authResult -> {
                    Log.i(TAG, "doSignUp: " + authResult.getUser().getEmail() + " created.");
                    startActivity(new Intent(this, MainActivity.class));
                }).addOnFailureListener(e -> {
                    Log.e(TAG, "doSignUp: " + e.toString());
                    alertsHelper.shortSimpleSnackbar(binding.getRoot(), e.getLocalizedMessage());
                });

    }

    private void forgotPwd(){
        String email = binding.loginEmail.getEditText().getText().toString();

        if(email == null || email.isEmpty()){
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), "El email no puede estar vacio.");
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnSuccessListener(unused ->
                alertsHelper.shortSimpleSnackbar(binding.getRoot(), String.format("Revise el correo %s con mas instrucciones.", email)));
    }
}