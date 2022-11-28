package com.example.aflo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SavedTripsFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference db;
    FirebaseUser user;
    FragmentActivity activity;

    ArrayList<TripSummaryRecord> trips;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_trips, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.savedTripsRecycler);

        activity = requireActivity();
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        db = database.getReference("Trips").child(user.getUid());

        db.get().addOnSuccessListener(dataSnapshot -> {
            if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                trips = getUserTrips(dataSnapshot.getChildren());

                SavedTripsRecycler adapter = new SavedTripsRecycler(activity, trips);

                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                recyclerView.setAdapter(adapter);
            } else {
                TextView title = activity.findViewById(R.id.savedTrips);
                title.setText("No saved trips");
            }
        }).addOnFailureListener(e -> Toast.makeText(activity.getApplicationContext(),
                "An error occurred", Toast.LENGTH_LONG).show());

        return view;
    }

    public ArrayList<TripSummaryRecord> getUserTrips(Iterable<DataSnapshot> snapshots) {
        ArrayList<TripSummaryRecord> trips = new ArrayList<>();

        for (DataSnapshot snap : snapshots) {
            trips.add(snap.getValue(TripSummaryRecord.class));
        }

        return trips;
    }
}
