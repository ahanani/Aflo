

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




    public void goToReturningFlightSelection(View view) {


        Intent goToReturningFlights = new Intent(this, FlightReturningSelection.class);
//        bundle.putInt("spentBudget", 1000);
        goToReturningFlights.putExtra("bundle", bundle);
        startActivity(goToReturningFlights);

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

    public void goToSiteLink (View view) {
        String url = "https://partners.api.skyscanner.net/apiservices/deeplink/v2?_cje=jzj5DawL5zJyT%2BnfeP9GJWfImnVvZd7vh0AJSObmdOp8YP07VbGmhzc%2BVTc80nUp&url=https%3A%2F%2Fwww.skyscanner.net%2Ftransport_deeplink%2F4.0%2FCA%2Fen-US%2FCAD%2Fccat%2F2%2F18411.12712.2022-11-26%2C12712.18411.2022-11-30%2Fair%2Ftrava%2Fflights%3Fitinerary%3Dflight%7C-32128%7C3121%7C18411%7C2022-11-26T09%3A00%7C16177%7C2022-11-26T10%3A24%7C84%7C-%7C-%7C-%3Bflight%7C-32128%7C3111%7C16177%7C2022-11-26T11%3A55%7C12712%7C2022-11-26T20%3A17%7C322%7C-%7C-%7C-%2Cflight%7C-32593%7C7%7C12712%7C2022-11-30T07%3A10%7C16177%7C2022-11-30T10%3A42%7C392%7C-%7C-%7C-%3Bflight%7C-32593%7C3321%7C16177%7C2022-11-30T11%3A45%7C18411%7C2022-11-30T12%3A48%7C63%7C-%7C-%7C-%26carriers%3D-32128%2C-32593%26operators%3D-31519%3B-32385%2C-32593%3B-31521%26passengers%3D1%26channel%3Ddataapi%26cabin_class%3Deconomy%26facilitated%3Dfalse%26fps_session_id%3D5b3b3252-9e05-4150-b014-1b07bc380bce%26ticket_price%3D708.27%26is_npt%3Dfalse%26is_multipart%3Dfalse%26client_id%3Dskyscanner_b2b%26request_id%3D44fdaef4-df88-427b-b5d8-aaffbd6fa345%26q_ids%3DH4sIAAAAAAAAAONS4GJJTk4sEWLmOOgmxczRo6vQsK51DptGw65Zc9iMmBQYAarT6uMiAAAA%7C-194627247284426001%7C2%26q_sources%3DJACQUARD%26commercial_filters%3Dfalse%26q_datetime_utc%3D2022-11-24T18%3A47%3A50%26pqid%3Dtrue";
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

}