package com.example.aflo;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RowRecyclerView extends RecyclerView.Adapter<RowRecyclerView.RowViewHolder> {

    Context context;
    ArrayList<String> hotels;
    ArrayList<Bitmap> images;
    ArrayList<String> prices;
    ArrayList<String> ratings;
    ArrayList<String> ratingsCount;
    private ItemClickListener clickListener;

    public RowRecyclerView(Context context, ArrayList<String> hotels, ArrayList<Bitmap> images,
                           ArrayList<String> prices, ArrayList<String> ratings, ArrayList<String> ratingsCount) {
        this.context = context;
        this.hotels = hotels;
        this.images = images;
        this.prices = prices;
        this.ratings = ratings;
        this.ratingsCount = ratingsCount;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_row, parent, false);
        return new RowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowViewHolder holder, int position) {
        holder.hotel.setText(hotels.get(position));
        String ratingText = ratings.get(position) + " (" + ratingsCount.get(position) + ")";
        holder.detail.setText(ratingText);
        String priceText = prices.get(position) + "/night";
        holder.price.setText(priceText);
        String progress = ratings.get(position);
        System.out.println("Abdullah Hanani " + progress);
        holder.ratingBar.setProgress(99, true);
        holder.image.setImageBitmap(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView hotel, detail, price;
        ImageView image;
        RatingBar ratingBar;

        public RowViewHolder(@NonNull View view) {
            super(view);
            hotel = view.findViewById(R.id.hotel);
            detail = view.findViewById(R.id.detail);
            image = view.findViewById(R.id.image);
            price = view.findViewById(R.id.price);
            ratingBar = view.findViewById(R.id.rating);
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
