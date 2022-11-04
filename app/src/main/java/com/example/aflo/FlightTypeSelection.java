package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class FlightTypeSelection extends AppCompatActivity {

    int flightType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_type_selection);




//        public void saveFlightTypeToBundle(View view) {
//            flightType = view.getId();
//            Log.d("flightType", flightType);
////            Log.d("Bundle", " " + bundle.getInt("BudgetSelection") + ", " + bundle.getString("OriginSelection") + ", " + bundle.getString("DestinationSelection") + ", " + bundle.getString("FromDateSelection") + ", " + bundle.getString("ToDateSelection"));
////        if (flightType == "@+id/FirstClass")
//
//        }

    }

    public void saveFlightTypeToBundle(View view) {
        // Get the intent
        Intent intent = getIntent();

        // Extract data from intent
//        Bundle bundle_flight = intent.getBundleExtra("bundle");
        Bundle bundle_flight = new Bundle();
        Intent intent1 = new Intent(this, FlightDepartingSelection.class);


        Button b = (Button) view;
        String text = b.getText().toString();
//            flightType = view.id
        Log.d("flightType", text);
//            Log.d("Bundle", " " + bundle.getInt("BudgetSelection") + ", " + bundle.getString("OriginSelection") + ", " + bundle.getString("DestinationSelection") + ", " + bundle.getString("FromDateSelection") + ", " + bundle.getString("ToDateSelection"));
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




//    public void toFlightDepartingSelection(View view) {
//
//        TextInputEditText flightType = findViewById(R.id.);
//        Bundle bundle = new Bundle();
//        bundle.putString("FromDateSelection", fromInput.getText().toString());
//        bundle.putString("ToDateSelection", toInput.getText().toString());
//        Log.d("Bundle", " " + bundle.getInt("BudgetSelection") + ", " + bundle.getString("OriginSelection") + ", " + bundle.getString("DestinationSelection") + ", " + bundle.getString("FromDateSelection") + ", " + bundle.getString("ToDateSelection"));
//        Intent intent = new Intent(this, HotelInformation.class);
//        startActivity(intent);
//    }


}