package com.example.aflo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewSummary extends RecyclerView.Adapter<RecyclerViewSummary.RowViewHolder> {

    Context context;
    ArrayList<String> titles;
    ArrayList<String> content;

    public RecyclerViewSummary(Context context, ArrayList<String> titles, ArrayList<String> content) {
        this.context = context;
        this.titles = titles;
        this.content = content;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.summary_row, parent, false);
        return new RowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.title2.setText(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {

        TextView title, title2;

        public RowViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            title2 = view.findViewById(R.id.title2);
        }
    }
}
