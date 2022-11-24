package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TripComplete extends AppCompatActivity {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_complete);

        bundle = getIntent().getExtras();

        Button returnToFlightSelection = findViewById(R.id.button4);
        returnToFlightSelection.setOnClickListener(this::toFlight);

        Button returnToHotelSelection = findViewById(R.id.button5);
        returnToHotelSelection.setOnClickListener(this::toHotel);

        Button finalize = findViewById(R.id.button8);
        finalize.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TripSummary.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    public void toFlight(View view){
        Intent intent = new Intent(this, FlightTypeSelection.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void toHotel(View view){
        Intent intent = new Intent(this, HotelInformation.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}