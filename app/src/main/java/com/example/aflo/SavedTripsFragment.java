package com.example.aflo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class SavedTripsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_trips, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.savedTripsRecycler);

        ArrayList<Object> trips = getUserTrips();
        SavedTripsRecycler adapter = new SavedTripsRecycler(getActivity(), trips);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    public ArrayList<Object> getUserTrips() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        assert user != null;
//        String uid = user.getUid();
        //TODO: Query realtime db using uid to get trips
        ArrayList<Object> trips = new ArrayList<>();
        trips.add(new Object());
        trips.add(new Object());
        trips.add(new Object());
        trips.add(new Object());
        trips.add(new Object());
        trips.add(new Object());
        return trips;
    }
}
