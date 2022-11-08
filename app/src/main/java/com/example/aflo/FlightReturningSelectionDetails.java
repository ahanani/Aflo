package com.example.aflo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FlightReturningSelectionDetails extends Fragment implements ItemClickListener {

    RecyclerView recyclerView;
    String[] flights, prices, stops;
    int[] images = {R.drawable.flight_logo_1, R.drawable.flight_logo_2, R.drawable.flight_logo_3, R.drawable.flight_logo_1,
            R.drawable.flight_logo_1, R.drawable.flight_logo_2, R.drawable.flight_logo_3, R.drawable.flight_logo_2,
            R.drawable.flight_logo_2, R.drawable.flight_logo_1, R.drawable.flight_logo_1};

    ConstraintLayout row1;
    boolean open = false;
    ConstraintLayout previouslyOpenRow = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flights = getResources().getStringArray(R.array.listOfDepartingFlightTimes);
        prices = getResources().getStringArray(R.array.listOfDepartingFlightPrices);
        stops = getResources().getStringArray(R.array.listOfDepartingFlightStops);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_flights, container, false);
        recyclerView = view.findViewById(R.id.rowViewFlight);
        flights = getResources().getStringArray(R.array.listOfDepartingFlightTimes);

        RowRecyclerViewReturningFlights rowRecyclerView = new RowRecyclerViewReturningFlights(getActivity(), flights, images, prices, stops);
        rowRecyclerView.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(rowRecyclerView);
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        row1 = view.findViewById(R.id.returning_flight_row);
        ViewGroup.LayoutParams params = row1.getLayoutParams();
        if (open) {
            open = false;
            previouslyOpenRow = row1;
            expandRow(row1, params);
        } else {
            if (previouslyOpenRow != null) {
                shrinkRow(previouslyOpenRow, previouslyOpenRow.getLayoutParams());
                previouslyOpenRow = null;
            }
            open = true;
            shrinkRow(row1, params);
        }
    }

    public void shrinkRow(ConstraintLayout row, ViewGroup.LayoutParams params) {
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        row.setLayoutParams(params);
        ImageView image = row.findViewById(R.id.flight_logo_return);
        ViewGroup.LayoutParams imageParams = image.getLayoutParams();
        imageParams.height = -2;
        imageParams.width = -2;
        image.setLayoutParams(imageParams);
        TextView seeDetails = row.findViewById(R.id.seeDetails_returning_flight);
        seeDetails.setVisibility(View.VISIBLE);
        TextView select = row.findViewById(R.id.select_returning_flight);
        TableLayout table = row.findViewById(R.id.features_returning_flight);
        table.removeAllViews();
        select.setVisibility(View.INVISIBLE);
    }

    public void expandRow(ConstraintLayout row, ViewGroup.LayoutParams params) {
        params.height = 1200;
        row.setLayoutParams(params);
        ImageView image = row.findViewById(R.id.flight_logo_return);
        ViewGroup.LayoutParams imageParams = image.getLayoutParams();
        imageParams.height = 500;
        imageParams.width = 500;
        image.setLayoutParams(imageParams);
        TextView seeDetails = row.findViewById(R.id.seeDetails_returning_flight);
        seeDetails.setVisibility(View.INVISIBLE);
        TextView select = row.findViewById(R.id.select_returning_flight);
        select.setVisibility(View.VISIBLE);
        TableLayout table = row.findViewById(R.id.features_returning_flight);
        TableRow flightType = new TableRow(getContext());
        TextView flightTypeText = new TextView(getContext());
        flightTypeText.setText("Air Canada - Economy");
        flightTypeText.setTextColor(Color.BLACK);
        flightTypeText.setTextSize(18);
        flightType.addView(flightTypeText);
        table.addView(flightType);
    }
}