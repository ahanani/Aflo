

package com.example.aflo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FlightDepartingSelection extends AppCompatActivity {
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_departing_selection);
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("bundle");

        FlightDepartingSelectionDetails flightDepartingFragment = new FlightDepartingSelectionDetails();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, flightDepartingFragment).commit();
    }

    public void goToReturningFlightSelection(View view) {


        Intent goToReturningFlights = new Intent(this, FlightReturningSelection.class);
        bundle.putInt("spentBudget", 1000);
        goToReturningFlights.putExtra("bundle", bundle);
        startActivity(goToReturningFlights);

    }

    public void home(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
//    public void goToHotelSelection(View view) {
//        Intent goToHotels = new Intent(this, HotelInformation.class);
//        startActivity(goToHotels);
//    }
}