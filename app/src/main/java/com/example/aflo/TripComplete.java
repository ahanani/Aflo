package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class TripComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_complete);

        Bundle bundle = getIntent().getBundleExtra("bundle");

        Button returnToFlightSelection = findViewById(R.id.button4);

        returnToFlightSelection.setOnClickListener(v -> toFlight(bundle));

        Button returnToHotelSelection = findViewById(R.id.button5);
        returnToHotelSelection.setOnClickListener(v -> toHotel(bundle));

        Button finalize = findViewById(R.id.button8);
        finalize.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), TripSummary.class);
            intent1.putExtra("bundle", bundle);
            startActivity(intent1);
        });
    }

    public void toFlight(Bundle bundle){
        Intent intent2 = new Intent(this, FlightTypeSelection.class);
        intent2.putExtra("bundle", bundle);
        startActivity(intent2);
    }

    public void toHotel(Bundle bundle){
        Intent intent3 = new Intent(this, HotelInformation.class);
        intent3.putExtra("bundle", bundle);
        startActivity(intent3);
    }
}