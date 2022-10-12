package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

public class FlightDepartingSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_departing_selection);

        String s= "Select your departing flight (MM/DD/YYYY - AIRPORT_CODE)";
        SpannableString ss1=  new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0,28, 0); // set size
        ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 28, 0);// set color
        TextView tv= (TextView) findViewById(R.id.select_your_departing_flight_textview);
        tv.setText(ss1);



    }
}