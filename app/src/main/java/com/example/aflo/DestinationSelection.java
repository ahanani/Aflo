package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class DestinationSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_selection);
    }

    public void toDateSelection(View view) {
        TextInputEditText input = findViewById(R.id.DestinationSelectionInput);
        Bundle bundle = new Bundle();
        bundle.putString("DestinationSelection", input.getText().toString());
        Intent intent = new Intent(this, DateSelection.class);
        startActivity(intent, bundle);
    }
}