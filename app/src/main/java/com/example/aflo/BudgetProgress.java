package com.example.aflo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BudgetProgress extends Fragment {
    private int totalBudget;

    public BudgetProgress() {
        super(R.layout.fragment_progress_bar);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = requireActivity().getIntent().getBundleExtra("bundle");

        totalBudget = bundle.getInt("budget");
        int spentBudget = bundle.getInt("spentBudget");

        Log.d("BudgetBar", "Set totalBudget to " + totalBudget);

        updateProgress(spentBudget);
    }

    public void updateProgress(int spentBudget) {
        ProgressBar progressBar = requireActivity().findViewById(R.id.progressBar);
        TextView remainingBudgetView = requireActivity().findViewById(R.id.remainingBudget);

        progressBar.setMin(0);
        progressBar.setMax(totalBudget);
        progressBar.setProgress(spentBudget, true);

        remainingBudgetView.setText("$" + (totalBudget - spentBudget) + " remaining");
        Log.d("BudgetBar", "Updated spentBudget to " + spentBudget);
    }
}
