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
//    String[] hotels, prices, stops;
    ArrayList<String> dates, prices, stops;
    ArrayList<Integer> images;
//    int[] images;
    private ItemClickListener clickListener;

    public RowRecyclerViewDepartingFlights(Context context, ArrayList<String> dates, ArrayList<Integer> images, ArrayList<String> prices, ArrayList<String> stops) {
        this.context = context;
        this.dates = dates;
        this.images = images;
        this.prices = prices;
        this.stops = stops;
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
        holder.flight.setText(dates.get(position));
//        holder.flight.setText(dates.get(position) + " - " + dates.get(position+1));
        holder.prices.setText(prices.get(position));
        holder.image.setImageResource(images.get(position));
        holder.stops.setText(stops.get(position));
    }

    @Override
    public int getItemCount() {
//        return images.length;
        return images.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView flight, stops, prices;
        ImageView image;

        public RowViewHolder(@NonNull View view) {
            super(view);
            flight = view.findViewById(R.id.flight);
            stops = view.findViewById(R.id.departing_flight_stops);
            prices = view.findViewById(R.id.price);
            image = view.findViewById(R.id.flight_logo);
//            carrierName = view.findViewById()
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
