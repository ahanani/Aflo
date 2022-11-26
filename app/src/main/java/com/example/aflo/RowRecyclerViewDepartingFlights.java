package com.example.aflo;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RowRecyclerViewDepartingFlights extends RecyclerView.Adapter<RowRecyclerViewDepartingFlights.RowViewHolder> {

    Context context;
    ArrayList<FlightPackage> flightPackages;
    private ItemClickListener clickListener;

    public RowRecyclerViewDepartingFlights(Context context, ArrayList<FlightPackage> flightPackages) {
        this.context = context;
        this.flightPackages = flightPackages;
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

        holder.departing_shortened_text.setText(flightPackage.getOutboundId());
        holder.returning_shortened_text.setText(flightPackage.getInboundId());
        holder.price.setText("" + flightPackage.getPrice());
    }

    @Override
    public int getItemCount() {
//        return images.length;
        return flightPackages.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView departing_shortened_text, returning_shortened_text, price;

        public RowViewHolder(@NonNull View view) {
            super(view);
            departing_shortened_text = view.findViewById(R.id.departing_shortened_text);
            returning_shortened_text = view.findViewById(R.id.returning_shortened_text);
            price = view.findViewById(R.id.price);

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
