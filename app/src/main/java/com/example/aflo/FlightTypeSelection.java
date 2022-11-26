package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class FlightTypeSelection extends AppCompatActivity {
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_type_selection);

        System.out.println("bundle info below 10:24\n" + savedInstanceState);
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
    }


    public String airportFinder(String cityInput, String countryIDInput) {
        String airportCode = ""; // what we will ultimately return

        String json = null;
        try {
            InputStream is = this.getAssets().open("country_city_airport.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("Continents");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                for (int j = 0; j < jo_inside.getJSONArray("Countries").length(); j++) {
                    String countryID = jo_inside.getJSONArray("Countries").getJSONObject(j).getString("Id");
                    if (countryID.equals(countryIDInput)) {
                        for (int z = 0; z < jo_inside.getJSONArray("Countries").getJSONObject(j).getJSONArray("Cities").length(); z++){
                            JSONObject canadianCities = jo_inside.getJSONArray("Countries").getJSONObject(j).getJSONArray("Cities").getJSONObject(z);
                            if (canadianCities.getString("Name").equals(cityInput)) {
                                airportCode = canadianCities.getString("IataCode");
                            }

                        }

                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return airportCode;
    }










    public void saveFlightTypeToBundle(View view) {

//        Bundle bundle_flight = new Bundle();
//        Intent intent1 = new Intent(this, FlightDepartingSelection.class);
        Log.d("FlightTypeSelection", "Bundle received: " + bundle);
        Log.d("FlightTypeSelection", "Bundle budget: " + bundle.getInt("budget"));

        Log.d("FlightTypeSelection", "Bundle OriginCity: " + bundle.getString("OriginCity"));
        Log.d("FlightTypeSelection", "Bundle OriginCode: " + bundle.getString("OriginCode"));
        Log.d("FlightTypeSelection", "Bundle fromYear: " + bundle.getInt("fromYear"));
        Log.d("FlightTypeSelection", "Bundle fromMonth: " + bundle.getInt("fromMonth"));
        Log.d("FlightTypeSelection", "Bundle fromDay: " + bundle.getInt("fromDay"));

        Log.d("FlightTypeSelection", "Bundle DestinationCity: " + bundle.getString("DestinationCity"));
        Log.d("FlightTypeSelection", "Bundle DestinationCode: " + bundle.getString("DestinationCode"));
        Log.d("FlightTypeSelection", "Bundle toYear: " + bundle.getInt("toYear"));
        Log.d("FlightTypeSelection", "Bundle toMonth: " + bundle.getInt("toMonth"));
        Log.d("FlightTypeSelection", "Bundle toDay: " + bundle.getInt("toDay"));


        String originAirportCode = airportFinder(bundle.getString("OriginCity"), bundle.getString("OriginCode"));
        String destinationAirportCode = airportFinder(bundle.getString("DestinationCity"), bundle.getString("DestinationCode"));
        System.out.println("printing the airport codes being passed to FlightDepartingSelection");
        System.out.println(originAirportCode);
        System.out.println(destinationAirportCode);

        bundle.putString("originAirportCode", originAirportCode);
        bundle.putString("destinationAirportCode", destinationAirportCode);


        Button b = (Button) view;
        String text = b.getText().toString();

        Intent intent1 = new Intent(view.getContext(), FlightDepartingSelection.class);

        if (text.equals("Economy                    $")) {
            bundle.putString("flightType", "Economy");
//            bundle.putInt("minFlightPrice", 0);
//            bundle.putInt("maxFlightPrice", 500);
            intent1.putExtra("bundle", bundle);
//            Log.d("flightType in bundle", bundle.getString("flightType"));

            startActivity(intent1);
        }

        if (text.equals("Business                  $$")) {
            bundle.putString("flightType", "Business");
//            bundle.putInt("minFlightPrice", 500);
//            bundle.putInt("maxFlightPrice", 2000);
            intent1.putExtra("bundle", bundle);
//            Log.d("flightType in bundle", bundle.getString("flightType"));

            startActivity(intent1);
        }

        if (text.equals("First Class           $$$")) {
            bundle.putString("flightType", "First");
//            bundle.putInt("minFlightPrice", 2000);
//            bundle.putInt("maxFlightPrice", 1000000);
            intent1.putExtra("bundle", bundle);
//            Log.d("flightType in bundle", bundle.getString("flightType"));

            startActivity(intent1);
        }
    }
}