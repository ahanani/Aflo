package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class BudgetSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_selection);
    }

    public void toOriginSelection(View view) {
        TextInputEditText input = findViewById(R.id.budgetSelectionInput);
        Bundle bundle = new Bundle();
        Log.d("Budget", ""+Integer.parseInt(input.getText().toString()));
        bundle.putInt("BudgetSelection",Integer.parseInt(input.getText().toString()));
        Intent intent = new Intent(this, OriginSelection.class);
        startActivity(intent, bundle);
    }
}