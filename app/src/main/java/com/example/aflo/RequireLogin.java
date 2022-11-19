package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RequireLogin extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_require_login);
    }

    public void redirectToSignup(View view) {
        Intent signUp = new Intent(this, Signup.class);
        FirebaseAuth.getInstance().signOut();
        startActivity(signUp);
    }
}
