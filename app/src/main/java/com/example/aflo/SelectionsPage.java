package com.example.aflo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class SelectionsPage extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private int num_clicked = 0;
    private boolean fromClicked = false;
    private boolean toClicked = false;
    private Bundle bundle;

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
        bundle = new Bundle();

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
                            fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                                @Override
                                public void onFragmentResumed(FragmentManager fm, Fragment f) {
                                    super.onFragmentResumed(fm, f);
                                    EditText fromDate = findViewById(R.id.fromSelectionInput);
                                    fromDate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            fromClicked = true;
                                            com.example.aflo.DatePicker mDatePickerDialogFragment;
                                            mDatePickerDialogFragment = new com.example.aflo.DatePicker();
                                            mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
                                        }
                                    });
                                    EditText toDate = findViewById(R.id.toSelectionInput);
                                    toDate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            toClicked = true;
                                            com.example.aflo.DatePicker mDatePickerDialogFragment;
                                            mDatePickerDialogFragment = new com.example.aflo.DatePicker();
                                            mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
                                        }
                                    });
                                }
                            }, true);
                        }
                        break;
                    case 3:
                        EditText to = (EditText) findViewById(R.id.toSelectionInput);
                        String toDate = to.getText().toString();
                        EditText from = (EditText) findViewById(R.id.fromSelectionInput);
                        String fromDate = from.getText().toString();
                        Log.d("budget", ""+bundle.getInt("budget"));
                        Log.d("origin", ""+bundle.getString("origin"));
                        Log.d("destination", ""+bundle.getString("destination"));
                        Log.d("toDay", ""+bundle.getInt("toDay"));
                        Log.d("toMonth", ""+bundle.getInt("toMonth"));
                        Log.d("toYear", ""+bundle.getInt("toYear"));
                        Log.d("fromDay", ""+bundle.getInt("fromDay"));
                        Log.d("fromMonth", ""+bundle.getInt("fromMonth"));
                        Log.d("fromYear", ""+bundle.getInt("fromYear"));
                        if ((toDate.matches("") && fromDate.matches(""))) {
                            Toast.makeText(this, "Please enter departing and returning date", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(this, FlightTypeSelection.class);
                            Log.d("SelectionsPage", "Bundle complete: " + bundle);
                            intent.putExtra("bundle", bundle);
                            startActivity(intent, bundle);
                        }
                        break;
                }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (fromClicked) {
            EditText from = findViewById(R.id.fromSelectionInput);
            String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
            from.setText(selectedDate);
            bundle.putInt("fromYear", year);
            bundle.putInt("fromMonth", month + 1);
            bundle.putInt("fromDay", dayOfMonth);
            fromClicked = false;
        } else if (toClicked) {
            EditText from = findViewById(R.id.toSelectionInput);
            String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
            from.setText(selectedDate);
            bundle.putInt("toYear", year);
            bundle.putInt("toMonth", month + 1);
            bundle.putInt("toDay", dayOfMonth);
            toClicked = false;
        }
    }
}