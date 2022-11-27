package com.example.aflo;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class RowRecyclerViewDepartingFlights extends RecyclerView.Adapter<RowRecyclerViewDepartingFlights.RowViewHolder> {

    Context context;
    public ArrayList<FlightPackage> flightPackages;
    private ItemClickListener clickListener;
    Locale locale;


    public RowRecyclerViewDepartingFlights(Context context, ArrayList<FlightPackage> flightPackages) {
        this.context = context;
        this.flightPackages = flightPackages;
        this.locale = new Locale("en", "US");
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_row_flights_departing, parent, false);
        return new RowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowViewHolder holder, int position) {
        FlightPackage flightPackage = flightPackages.get(position);
        flightPackage.setId(position);
        flightPackage.setHolder(holder);
        String fmtPrice = "$%d";

        holder.departing_shortened_text.setText(flightPackage.getOutboundDate());
        holder.returning_shortened_text.setText(flightPackage.getInboundDate());
        holder.price.setText(String.format(locale, fmtPrice, flightPackage.getPrice()));
        holder.visit_site_button.setOnClickListener(view -> {
            Intent goToLink = new Intent(Intent.ACTION_VIEW, Uri.parse(flightPackage.getDeeplink()));
            context.startActivity(goToLink);
        });
    }

    public void updateView(@NonNull RowViewHolder holder, int position) {
        FlightPackage flightPackage = flightPackages.get(position);
        flightPackage.setExpanded(true);

        String fmtPlace = "%s (%s)";
        String fmtStops = "%d stops";
        String fmtAirlineInfo = "%s - %s";

        holder.departing_date.setText(flightPackage.getOutboundDate());
        holder.departing_city_and_airport.setText(
                String.format(fmtPlace, flightPackage.getOutboundCity(), flightPackage.getOutboundAirportCode())
        );
        holder.departing_stops.setText(String.format(locale, fmtStops, flightPackage.getOutboundSegments() - 1));
        holder.departing_airline_and_class.setText(String.format(locale, fmtAirlineInfo,
                flightPackage.getOutboundCarrier(), flightPackage.getCabinClass()));

        holder.returning_date.setText(flightPackage.getInboundDate());
        holder.returning_city_and_airport.setText(
                String.format(locale, fmtPlace, flightPackage.getInboundCity(), flightPackage.getInboundAirportCode())
        );
        holder.returning_stops.setText(String.format(locale, fmtStops, flightPackage.getInboundSegments() - 1));
        holder.returning_airline_and_class.setText(String.format(locale, fmtAirlineInfo,
                flightPackage.getInboundCarrier(), flightPackage.getCabinClass()));

    }

    @Override
    public int getItemCount() {
        return flightPackages.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView departing_shortened_text, returning_shortened_text, price,
                departing_date, departing_city_and_airport, departing_stops,
                departing_airline_and_class, returning_city_and_airport, returning_date,
                returning_stops, returning_airline_and_class;
        Button visit_site_button;

        public RowViewHolder(@NonNull View view) {
            super(view);
            departing_shortened_text = view.findViewById(R.id.departing_shortened_text);
            returning_shortened_text = view.findViewById(R.id.returning_shortened_text);
            price = view.findViewById(R.id.price);

            departing_date = view.findViewById(R.id.departing_date);
            departing_city_and_airport = view.findViewById(R.id.departing_city_and_airport);
            departing_stops = view.findViewById(R.id.departing_stops);
            departing_airline_and_class = view.findViewById(R.id.departing_airline_and_class);

            returning_date = view.findViewById(R.id.returning_date);
            returning_city_and_airport = view.findViewById(R.id.returning_city_and_airport);
            returning_stops = view.findViewById(R.id.returning_stops);
            returning_airline_and_class = view.findViewById(R.id.returning_airline_and_class);

            visit_site_button = view.findViewById(R.id.visit_site_button);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null) {
                clickListener.onClick(v, getAdapterPosition());
            }
        }
    }
}
