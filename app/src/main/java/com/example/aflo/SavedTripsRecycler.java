package com.example.aflo;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SavedTripsRecycler extends RecyclerView.Adapter<SavedTripsRecycler.SavedTripsHolder> {
    @NonNull
    @Override
    public SavedTripsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedTripsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SavedTripsHolder extends RecyclerView.ViewHolder {

        public SavedTripsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
