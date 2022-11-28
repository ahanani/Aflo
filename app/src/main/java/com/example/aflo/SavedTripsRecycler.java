package com.example.aflo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SavedTripsRecycler extends RecyclerView.Adapter<SavedTripsRecycler.SavedTripsHolder> {
    private Context context;
    private ArrayList<TripSummaryRecord> savedTrips;
    private Locale locale;

    public SavedTripsRecycler(Context c, ArrayList<TripSummaryRecord> trip) {
        context = c;
        savedTrips = trip;
        locale = new Locale("en", "US");
    }

    @NonNull
    @Override
    public SavedTripsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View tripView = inflater.inflate(R.layout.fragment_saved_trip, parent, false);
        return new SavedTripsHolder((tripView));
    }

    @Override
    public void onBindViewHolder(@NonNull SavedTripsHolder holder, int position) {
        TripSummaryRecord trip = savedTrips.get(position);

        String priceFmt = "$%d";
        String locationFmt = "%s, %s";
        String dateFmt = "%s %d, %d";

        holder.fromDate.setText(String.format(locale, dateFmt,
                formatMonth(trip.getFromMonth()), trip.getFromDay(), trip.getFromYear()));
        holder.toDate.setText(String.format(locale, dateFmt,
                formatMonth(trip.getToMonth()), trip.getToDay(), trip.getToYear()));
        holder.origin.setText(String.format(locale, locationFmt, trip.getOriginCity(), trip.getOriginCountry()));
        holder.destination.setText(String.format(locale, locationFmt, trip.getDestinationCity(), trip.getDestinationCountry()));
        holder.total.setText(String.format(locale, priceFmt, trip.getSpentBudget()));

        holder.viewSummary.setOnClickListener(view -> {
            Intent viewSummaryRecord = new Intent(context, TripSummary.class);
            viewSummaryRecord.putExtra("mode", "nosaving");
            viewSummaryRecord.putExtra("bundle", trip.toBundle());
            context.startActivity(viewSummaryRecord);
        });

    }

    @Override
    public int getItemCount() {
        return savedTrips.size();
    }

    public class SavedTripsHolder extends RecyclerView.ViewHolder {
        TextView fromDate, toDate, origin, destination, total;
        Button viewSummary;

        public SavedTripsHolder(@NonNull View itemView) {
            super(itemView);
            fromDate = itemView.findViewById(R.id.dateAVS);
            toDate = itemView.findViewById(R.id.dateBVS);
            origin = itemView.findViewById(R.id.originV);
            destination = itemView.findViewById(R.id.destinationV);
            total = itemView.findViewById(R.id.budgetV);
            viewSummary = itemView.findViewById(R.id.viewSummary);
        }
    }

    public String formatMonth(int month) {
        SimpleDateFormat monthParse = new SimpleDateFormat("MM");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMM");
        try {
            return monthDisplay.format(monthParse.parse(Integer.toString(month)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
