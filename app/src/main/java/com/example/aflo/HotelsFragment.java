package com.example.aflo;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;


public class HotelsFragment extends Fragment implements ItemClickListener {

    RecyclerView recyclerView;
    String[] hotels;
    int[] images = {R.drawable.hotel1, R.drawable.hotel2, R.drawable.hotel3, R.drawable.hotel4,
            R.drawable.hotel5, R.drawable.hotel6, R.drawable.hotel7, R.drawable.hotel8,
            R.drawable.hotel9, R.drawable.hotel10, R.drawable.hotel11};

    ConstraintLayout row;
    boolean open = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hotels = getResources().getStringArray(R.array.listOfHotels);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hotels, container, false);
        recyclerView = view.findViewById(R.id.rowView);
        hotels = getResources().getStringArray(R.array.listOfHotels);

        RowRecyclerView rowRecyclerView = new RowRecyclerView(getActivity(), hotels, images);
        rowRecyclerView.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(rowRecyclerView);
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        row = view.findViewById(R.id.hotel_row);
        ViewGroup.LayoutParams params = row.getLayoutParams();
        if (open) {
            open = false;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            row.setLayoutParams(params);
            ImageView image = row.findViewById(R.id.image);
            ViewGroup.LayoutParams imageParams = image.getLayoutParams();
            imageParams.height = -2;
            imageParams.width = -2;
            image.setLayoutParams(imageParams);
            TextView seeDetails = row.findViewById(R.id.seeDetails);
            seeDetails.setVisibility(View.VISIBLE);
            TextView select = row.findViewById(R.id.select);
            TableLayout table = row.findViewById(R.id.features);
            table.removeAllViews();
            select.setVisibility(View.INVISIBLE);
        } else {
            open = true;
            params.height = 1200;
            row.setLayoutParams(params);
            ImageView image = row.findViewById(R.id.image);
            ViewGroup.LayoutParams imageParams = image.getLayoutParams();
            imageParams.height = 500;
            imageParams.width = 500;
            image.setLayoutParams(imageParams);
            TextView seeDetails = row.findViewById(R.id.seeDetails);
            seeDetails.setVisibility(View.INVISIBLE);
            TextView select = row.findViewById(R.id.select);
            select.setVisibility(View.VISIBLE);
            TableLayout table = row.findViewById(R.id.features);
            TableRow wifi = new TableRow(getContext());
            TextView wifiText = new TextView(getContext());
            wifiText.setText("Wifi");
            wifiText.setTextColor(Color.BLACK);
            wifiText.setTextSize(18);
            wifi.addView(wifiText);
            table.addView(wifi);
        }
    }
}