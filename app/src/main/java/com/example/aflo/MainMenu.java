package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }

    public void planTrip(View view) {
        Intent intent = new Intent(this, SelectionsPage.class);
        startActivity(intent);
    }

    public void seeSavedTrips(View view) {
        Intent intent;
        if (FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
            intent = new Intent(this, RequireLogin.class);
        } else {
            intent = new Intent(this, SavedTripsActivity.class);
        }

        startActivity(intent);
    }

    public void goToSettings(View view) {
        Intent intent = new Intent(this, AccountSettings.class);
        startActivity(intent);
    }
}