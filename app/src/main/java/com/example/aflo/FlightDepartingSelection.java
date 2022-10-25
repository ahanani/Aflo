package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FlightDepartingSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_departing_selection);

        // top text for dates
        String s= "Select your departing flight (Oct 18, 2022 - YVR)";
        SpannableString ss1=  new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0,28, 0); // set size
        ss1.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);// set color
        TextView tv= (TextView) findViewById(R.id.select_your_departing_flight_textview);
        tv.setText(ss1);


        // fragments --> flight selections
        Fragment selection = new flight_departing_selection();
        Fragment details = new FlightDepartingSelectionDetails();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, selection);
        fragmentTransaction.commit();


        ConstraintLayout firstSelection = findViewById(R.id.firstChoice);
//        firstSelection.setOnClickListener(view -> {
//            FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
//            fragmentTransaction2.replace(R.id.fragment, details);
//            fragmentTransaction2.commit();
//        });



    }
}