package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SavedTripsActivity extends AppCompatActivity {
    FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
        setContentView(R.layout.fragment_saved_trips);
    }
}
