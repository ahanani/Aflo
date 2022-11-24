package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;

public class TripSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_summary);
        Bundle bundle = getIntent().getExtras();
        int toDay = bundle.getInt("toDay");
        int toMonth = bundle.getInt("toMonth");
        int toYear = bundle.getInt("toYear");
        int fromDay = bundle.getInt("fromDay");
        int fromMonth = bundle.getInt("fromMonth");
        int fromYear = bundle.getInt("fromYear");

        String month = Month.of(toMonth).toString();

        System.out.println("The date is: " + month);

        ArrayList<String> titles = new ArrayList<>();
        titles.add("Origin");
        titles.add("Destination");
        titles.add("Destination");
        ArrayList<String> content = new ArrayList<>();
        content.add("Abdullah");

//        RecyclerView recyclerView = findViewById(R.id.rowView);
//        RecyclerViewSummary recyclerViewSummary = new RecyclerViewSummary(this,
//                titles, content);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(recyclerViewSummary);

        DonutSection section1 = new DonutSection("section_1", Color.parseColor("#FFA500"),1f);

        DonutSection section2 = new DonutSection("section_2", Color.parseColor("#FFED00"), 1f);

        DonutSection section3 = new DonutSection("section_3", Color.parseColor("#FFFFFF"), 3f);

        DonutProgressView donut_view = findViewById(R.id.donut_view);
        donut_view.setCap(5f);
        donut_view.setAnimationDurationMs(3000);
        donut_view.setStrokeWidth(70);
        List<DonutSection> list = new ArrayList<DonutSection>();
        list.add(section1);
        list.add(section2);
        list.add(section3);
        donut_view.submitData(list);

    }
}