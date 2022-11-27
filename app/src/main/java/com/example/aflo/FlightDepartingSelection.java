

package com.example.aflo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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


//        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        Log.d("FlightDepartingSelection", "Bundle received: " + bundle);
        Log.d("FlightDepartingSelection", "Bundle budget: " + bundle.getInt("budget"));
        Log.d("FlightDepartingSelection", "Bundle destination: " + bundle.getString("destination"));
        Log.d("FlightDepartingSelection", "Bundle fromYear: " + bundle.getInt("fromYear"));
        Log.d("FlightDepartingSelection", "Bundle fromMonth: " + bundle.getInt("fromMonth"));
        Log.d("FlightDepartingSelection", "Bundle fromDay: " + bundle.getInt("fromDay"));

        Log.d("FlightDepartingSelection", "Bundle origin: " + bundle.getString("origin"));
        Log.d("FlightDepartingSelection", "Bundle toYear: " + bundle.getInt("toYear"));
        Log.d("FlightDepartingSelection", "Bundle toMonth: " + bundle.getInt("toMonth"));
        Log.d("FlightDepartingSelection", "Bundle toDay: " + bundle.getInt("toDay"));

        Log.d("FlightDepartingSelection", "Bundle minFlightPrice: " + bundle.getInt("minFlightPrice"));
        Log.d("FlightDepartingSelection", "Bundle maxFlightPrice: " + bundle.getInt("maxFlightPrice"));


        Log.d("test formatMonth", formatMonth("2"));

        String monthOut = formatMonth(Integer.toString(bundle.getInt("fromMonth")));
        String monthIn = formatMonth(Integer.toString(bundle.getInt("toMonth")));


        String replacedHeaderText = String.format("(%s %d, %d - %s %d, %d)", monthOut,bundle.getInt("fromDay"),bundle.getInt("fromYear"),monthIn, bundle.getInt("toDay"),bundle.getInt("toYear"));
        Log.d("replacedHeaderText", replacedHeaderText);

        TextView t1 = findViewById(R.id.dates);
        t1.setText(replacedHeaderText);


    }

    public String formatMonth(String month) {
        SimpleDateFormat monthParse = new SimpleDateFormat("MM");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMM");
        try {
            return monthDisplay.format(monthParse.parse(month));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void home(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void goToHotelSelection(View view) {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");


        Intent goToHotels = new Intent(this, HotelInformation.class);
        bundle.putInt("spentBudget", bundle.getInt("spentBudget") + bundle.getInt("flightPrice"));
        goToHotels.putExtra("bundle", bundle);
        startActivity(goToHotels);
    }

}