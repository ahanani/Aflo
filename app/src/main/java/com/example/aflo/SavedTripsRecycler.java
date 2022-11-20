package com.example.aflo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedTripsRecycler extends RecyclerView.Adapter<SavedTripsRecycler.SavedTripsHolder> {
    private Context context;
    private ArrayList<Object> savedTrips;

    public SavedTripsRecycler(Context c, ArrayList<Object> trip) {
        //TODO: Change trip type Object to type Trip class
        context = c;
        savedTrips = trip;
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
        //TODO: Change trip type Object to type Trip class
        Object trip = savedTrips.get(position);

        holder.fromDate.setText("Jan XX, 20XX");
        holder.toDate.setText("Mar XX, 20XX");
        holder.origin.setText("City, CO");
        holder.destination.setText("City, CO");
        holder.total.setText("$0000.00");

    }

    @Override
    public int getItemCount() {
        return savedTrips.size();
    }

    public class SavedTripsHolder extends RecyclerView.ViewHolder {
        TextView fromDate, toDate, origin, destination, total;

        public SavedTripsHolder(@NonNull View itemView) {
            super(itemView);
            fromDate = itemView.findViewById(R.id.dateAVS);
            toDate = itemView.findViewById(R.id.dateBVS);
            origin = itemView.findViewById(R.id.originV);
            destination = itemView.findViewById(R.id.destinationV);
            total = itemView.findViewById(R.id.budgetV);
        }
    }
}
