package com.example.aflo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowRecyclerView extends RecyclerView.Adapter<RowRecyclerView.RowViewHolder> {

    Context context;
    String[] hotels;
    int[] images;
    private ItemClickListener clickListener;

    public RowRecyclerView(Context context, String[] hotels, int[] images) {
        this.context = context;
        this.hotels = hotels;
        this.images = images;
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
        holder.hotel.setText(hotels[position]);
        holder.detail.setText("Rating");
        holder.image.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView hotel, detail;
        ImageView image;

        public RowViewHolder(@NonNull View view) {
            super(view);
            hotel = view.findViewById(R.id.hotel);
            detail = view.findViewById(R.id.detail);
            image = view.findViewById(R.id.image);
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
