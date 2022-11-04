package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class FlightTypeSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_type_selection);
    }

    public void saveFlightTypeToBundle(View view) {

        Bundle bundle_flight = new Bundle();
        Intent intent1 = new Intent(this, FlightDepartingSelection.class);


        Button b = (Button) view;
        String text = b.getText().toString();

        if (text.equals("Economy                    $")) {
            bundle_flight.putString("flightType", "Economy");
            Log.d("flightType in bundle", bundle_flight.getString("flightType"));
            startActivity(intent1);
        }

        if (text.equals("Business                  $$")) {
            bundle_flight.putString("flightType", "Business");
            Log.d("flightType in bundle", bundle_flight.getString("flightType"));
            startActivity(intent1);
        }

        if (text.equals("First Class           $$$")) {
            bundle_flight.putString("flightType", "Business");
            Log.d("flightType in bundle", bundle_flight.getString("flightType"));
            startActivity(intent1);
        }
    }
}