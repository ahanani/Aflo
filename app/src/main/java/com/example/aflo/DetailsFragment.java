package com.example.aflo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hotel_details, container, false);
        TextView name = view.findViewById(R.id.name);
        ImageView imageView = view.findViewById(R.id.imageView);
        assert getArguments() != null;
        name.setText(getArguments().getString("name"));
        imageView.setImageResource(getArguments().getInt("image"));
        Button back = view.findViewById(R.id.back);
        back.setOnClickListener(View -> {
            HotelsFragment hotelsFragment = new HotelsFragment();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment, hotelsFragment).commit();
        });

        return view;
    }
}
