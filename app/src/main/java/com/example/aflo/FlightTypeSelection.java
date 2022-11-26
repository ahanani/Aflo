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





    public void saveFlightTypeToBundle(View view) {

//        Bundle bundle_flight = new Bundle();
//        Intent intent1 = new Intent(this, FlightDepartingSelection.class);
        Log.d("FlightTypeSelection", "Bundle received: " + bundle);
        Log.d("FlightTypeSelection", "Bundle budget: " + bundle.getInt("budget"));
        Log.d("FlightTypeSelection", "Bundle destination: " + bundle.getString("destination"));
        Log.d("FlightTypeSelection", "Bundle fromYear: " + bundle.getInt("fromYear"));
        Log.d("FlightTypeSelection", "Bundle fromMonth: " + bundle.getInt("fromMonth"));
        Log.d("FlightTypeSelection", "Bundle fromDay: " + bundle.getInt("fromDay"));

        Log.d("FlightTypeSelection", "Bundle origin: " + bundle.getString("origin"));
        Log.d("FlightTypeSelection", "Bundle toYear: " + bundle.getInt("toYear"));
        Log.d("FlightTypeSelection", "Bundle toMonth: " + bundle.getInt("toMonth"));
        Log.d("FlightTypeSelection", "Bundle toDay: " + bundle.getInt("toDay"));




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