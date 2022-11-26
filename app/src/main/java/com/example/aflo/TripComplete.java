package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TripComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_complete);
    }

    public void toFlight(View view){
        Intent intent = new Intent(this, FlightTypeSelection.class);
        startActivity(intent);
    }

    public void toHotel(View view){
        Intent intent = new Intent(this, HotelInformation.class);
        startActivity(intent);
    }

    public void home(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}