

package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FlightDepartingSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_departing_selection);

        FlightDepartingSelectionDetails flightDepartingFragment = new FlightDepartingSelectionDetails();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, flightDepartingFragment).commit();
    }

    public void goToReturningFlightSelection(View view) {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        Intent goToReturningFlights = new Intent(this, FlightReturningSelection.class);
        goToReturningFlights.putExtra("bundle", bundle);
        startActivity(goToReturningFlights);

    }
//    public void goToHotelSelection(View view) {
//        Intent goToHotels = new Intent(this, HotelInformation.class);
//        startActivity(goToHotels);
//    }
}