package com.example.aflo;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HotelInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_information);
        HotelsFragment hotelsFragment = new HotelsFragment();
        Bundle bundle = getIntent().getExtras().getBundle("bundle");
        int toDay = bundle.getInt("toDay");
        int toMonth = bundle.getInt("toMonth");
        int toYear = bundle.getInt("toYear");
        int fromDay = bundle.getInt("fromDay");
        int fromMonth = bundle.getInt("fromMonth");
        int fromYear = bundle.getInt("fromYear");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date from = simpleDateFormat.parse(fromMonth+"/"+fromDay+"/"+fromYear);
            Date to = simpleDateFormat.parse(toMonth+"/"+toDay+"/"+toYear);

            assert to != null;
            assert from != null;
            long timeDiff = Math.abs(from.getTime() - to.getTime());
            long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

            TextView title = findViewById(R.id.textView);
            String titleText = "Select Your Hotel (" + daysDiff + " Nights)";
            title.setText(titleText);
            hotelsFragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainFragment, hotelsFragment).commit();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}