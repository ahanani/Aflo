package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class DateSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);
    }

    public void toFlightTypeSelection(View view) {
        TextInputEditText fromInput = findViewById(R.id.FromSelectionInput);
        TextInputEditText toInput = findViewById(R.id.ToSelectionInput);
        Bundle bundle = new Bundle();
        bundle.putString("FromDateSelection", fromInput.getText().toString());
        bundle.putString("ToDateSelection", toInput.getText().toString());
        Log.d("Bundle", " " + bundle.getInt("BudgetSelection") + ", " + bundle.getString("OriginSelection") + ", " + bundle.getString("DestinationSelection") + ", " + bundle.getString("FromDateSelection") + ", " + bundle.getString("ToDateSelection"));
        Intent intent = new Intent(this, HotelInformation.class);
        startActivity(intent);
    }
}