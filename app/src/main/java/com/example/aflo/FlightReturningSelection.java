

package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FlightReturningSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_returning_selection);

        FlightReturningSelectionDetails flightReturningFragment = new FlightReturningSelectionDetails();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, flightReturningFragment).commit();
    }

    public void goToHotelSelection(View view) {
        Intent goToHotels = new Intent(this, HotelInformation.class);
        startActivity(goToHotels);
    }
}