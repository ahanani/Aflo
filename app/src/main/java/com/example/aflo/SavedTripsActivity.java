package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SavedTripsActivity extends AppCompatActivity {
    FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_trips);
        user = FirebaseAuth.getInstance().getCurrentUser();

        //TODO: Change guard condition to "if User has no trips saved"
        if (false) {
            TextView title = findViewById(R.id.savedTrips);
            title.setText("No saved trips");
        }
    }
    public void home(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
