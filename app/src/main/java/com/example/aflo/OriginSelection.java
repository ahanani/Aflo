package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class OriginSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origin_selection);
    }

    public void toDestinationSelection(View view) {
        TextInputEditText input = findViewById(R.id.OriginSelectionInput);
        Bundle bundle = new Bundle();
        Log.d("Origin", input.getText().toString());
        bundle.putString("OriginSelection", input.getText().toString());
        Intent intent = new Intent(this, DestinationSelection.class);
        startActivity(intent, bundle);
    }
}