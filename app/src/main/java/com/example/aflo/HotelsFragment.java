package com.example.aflo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HotelsFragment extends Fragment implements ItemClickListener {

    RecyclerView recyclerView;
    String[] hotels;
    int[] images = {R.drawable.hotel1, R.drawable.hotel2, R.drawable.hotel3, R.drawable.hotel4,
            R.drawable.hotel5, R.drawable.hotel6, R.drawable.hotel7, R.drawable.hotel8,
            R.drawable.hotel9, R.drawable.hotel10, R.drawable.hotel11};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hotels = getResources().getStringArray(R.array.listOfHotels);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hotels, container, false);
        recyclerView = view.findViewById(R.id.rowView);
        hotels = getResources().getStringArray(R.array.listOfHotels);

        RowRecyclerView rowRecyclerView = new RowRecyclerView(getActivity(), hotels, images);
        rowRecyclerView.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(rowRecyclerView);
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", hotels[position]);
        bundle.putInt("image", images[position]);
        detailsFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment, detailsFragment).commit();

    }
}