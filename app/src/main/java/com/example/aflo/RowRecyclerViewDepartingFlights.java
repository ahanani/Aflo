package com.example.aflo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowRecyclerViewDepartingFlights extends RecyclerView.Adapter<RowRecyclerViewDepartingFlights.RowViewHolder> {

    Context context;
    String[] hotels, prices, stops;
    int[] images;
    private ItemClickListener clickListener;

    public RowRecyclerViewDepartingFlights(Context context, String[] hotels, int[] images, String[] prices, String[] stops) {
        this.context = context;
        this.hotels = hotels;
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
        holder.flight.setText(hotels[position]);
        holder.prices.setText(prices[position]);
        holder.image.setImageResource(images[position]);
        holder.stops.setText(stops[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
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
