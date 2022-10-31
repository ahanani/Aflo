package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SelectionsPage extends AppCompatActivity {

    private int num_clicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selections_page);
        Fragment budgetSelection = new BudgetSelectionFragment();
        Fragment destinationSelection = new DestinationSelectionFragment();
        Fragment originSelection = new OriginSelectionFragment();
        Fragment dateSelection = new DateSelectionFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.selectionsContainer, budgetSelection);
        fragmentTransaction.commit();
        ImageView forward = findViewById(R.id.forwardButton);
        Bundle bundle = new Bundle();


        forward.setOnClickListener(view -> {
                switch(num_clicked) {
                    case 0:
                        EditText budget = (EditText) findViewById(R.id.budgetSelectionInput);
                        String budgetNum = budget.getText().toString();
                        if (budgetNum.matches("")) {
                            Toast.makeText(this, "Please enter a budget", Toast.LENGTH_LONG).show();
                        }
                        else {
                            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                            fragmentTransaction1.replace(R.id.selectionsContainer, originSelection);
                            fragmentTransaction1.commit();
                            num_clicked++;
                            bundle.putInt("budget", Integer.parseInt(budgetNum));
                        }
                        break;
                    case 1:
                        EditText origin = (EditText) findViewById(R.id.originSelectionInput);
                        String originString = origin.getText().toString();
                        if (originString.matches("")) {
                            Toast.makeText(this, "Please enter an origin city", Toast.LENGTH_LONG).show();
                        }else {
                            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                            fragmentTransaction1.replace(R.id.selectionsContainer, destinationSelection);
                            fragmentTransaction1.commit();
                            num_clicked++;
                            bundle.putString("origin", originString);
                        }
                        break;
                    case 2:
                        EditText destination = (EditText) findViewById(R.id.destinationSelectionInput);
                        String destinationString = destination.getText().toString();
                        if (destinationString.matches("")) {
                            Toast.makeText(this, "Please enter an destination city", Toast.LENGTH_LONG).show();
                        } else {
                            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                            fragmentTransaction1.replace(R.id.selectionsContainer, dateSelection);
                            fragmentTransaction1.commit();
                            num_clicked++;
                            bundle.putString("destination", destinationString);
                        }
                        break;
                    case 3:
                        EditText to = (EditText) findViewById(R.id.toSelectionInput);
                        String toDate = to.getText().toString();
                        EditText from = (EditText) findViewById(R.id.fromSelectionInput);
                        String fromDate = from.getText().toString();
                        if (!(toDate.matches("^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d{2}$") && fromDate.matches("^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d{2}$"))) {
                            Toast.makeText(this, "Please enter a valid to and from date (MM/DD/YYYY)", Toast.LENGTH_LONG).show();
                        } else {
                            bundle.putString("to", toDate);
                            bundle.putString("from", fromDate);
                            Intent intent = new Intent(this, FlightTypeSelection.class);
                            startActivity(intent);
                        }
                        break;
                }
        });
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