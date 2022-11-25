package com.example.aflo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SelectionsPage extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private int num_clicked = 0;
    private boolean fromClicked = false;
    private boolean toClicked = false;
    private Bundle bundle;
    String country = "";
    ArrayList<String> states = new ArrayList<String>();
    JSONObject json = new JSONObject();
    ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
    Map<String, String> mapFinal;
    ArrayList<String> cities = new ArrayList<String>();


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
        bundle.putInt("spentBudget", 0);
        ArrayList<String> countries = new ArrayList<String>();
        ArrayList<JSONObject> statesObjs = new ArrayList<JSONObject>();
        String iso2 = "";


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


                            fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                                @Override
                                public void onFragmentResumed(FragmentManager fm, Fragment f) {
                                    super.onFragmentResumed(fm, f);
                                    Map<String, String> countryMap = new HashMap<>();
                                    for (String iso : Locale.getISOCountries()) {
                                        Locale l = new Locale("", iso);
                                        countries.add(l.getDisplayCountry());
                                        countryMap.put(l.getDisplayCountry(), iso);
                                    }
                                    mapFinal = countryMap;

                                    Collections.sort(countries);
                                    ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(SelectionsPage.this,
                                            android.R.layout.simple_spinner_item, countries);

                                    countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    AutoCompleteTextView spinner = findViewById(R.id.spinner);
                                    if (spinner != null) {
                                        spinner.setAdapter(countryAdapter);
                                        spinner.setThreshold(1);
                                        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                                                country = spinner.getText().toString();
                                                AutoCompleteTextView spinner21 = findViewById(R.id.spinner2);
                                                spinner21.setText("");
                                                AutoCompleteTextView spinner22 = findViewById(R.id.spinner3);
                                                spinner22.setText("");
                                                String code = countryMap.get(country);
                                                states = new ArrayList<String>();
                                                cities = new ArrayList<String>();


                                                ArrayList<JSONObject> statesJsons = new ArrayList<JSONObject>();
                                                try {
                                                    statesJsons = load(code);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                } catch (ParseException e) {
                                                    e.printStackTrace();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                for (JSONObject x: statesJsons){
                                                    try {
                                                        jsonList.add(x);
                                                        if (!states.contains(x.getString("state"))) {
                                                            states.add(x.getString("state"));
                                                        }
                                                        if (!cities.contains(x.getString("city"))) {
                                                            cities.add(x.getString("city"));
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                Collections.sort(cities);
                                                ArrayAdapter<String> countryAdapter2 = new ArrayAdapter<String>(SelectionsPage.this,
                                                        android.R.layout.simple_spinner_item, cities);

                                                countryAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                AutoCompleteTextView spinner2 = findViewById(R.id.spinner3);
                                                spinner2.setThreshold(2);//will start working from first character
                                                spinner2.setAdapter(countryAdapter2);


                                                Collections.sort(states);
                                                ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(SelectionsPage.this,
                                                        android.R.layout.simple_spinner_item, states);

                                                countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                AutoCompleteTextView spinner = findViewById(R.id.spinner2);
                                                spinner.setAdapter(countryAdapter);
                                                spinner.setThreshold(1);
                                                spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                                                        String state = spinner.getText().toString();
                                                        AutoCompleteTextView spinner52 = findViewById(R.id.spinner3);
                                                        spinner52.setText("");
                                                        cities = new ArrayList<String>();
                                                        for (int g = 0; g < jsonList.size(); g++) {
                                                            try {
                                                                if (jsonList.get(g).getString("state").matches(state) && !cities.contains(jsonList.get(g).getString("city"))) {
                                                                    cities.add(jsonList.get(g).getString("city"));
                                                                }
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                        Collections.sort(cities);
                                                        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(SelectionsPage.this,
                                                                android.R.layout.simple_spinner_item, cities);

                                                        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                        AutoCompleteTextView spinner = findViewById(R.id.spinner3);
                                                        spinner.setThreshold(1);//will start working from first character
                                                        spinner.setAdapter(countryAdapter);

                                                    }
                                                });
                                            }

                                        });
                                    }
                                }
                            }, true);


                        }
                        break;
                    case 1:
                        AutoCompleteTextView country = findViewById(R.id.spinner);
                        AutoCompleteTextView state = findViewById(R.id.spinner2);
                        AutoCompleteTextView city = findViewById(R.id.spinner3);

                        String cityString = city.getText().toString();
                        String stateString = state.getText().toString();
                        String countryString = country.getText().toString();

                        if (cityString.matches("") || countryString.matches("")) {
                            Toast.makeText(this, "Please enter an departure location", Toast.LENGTH_LONG).show();
                        } else {
                            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                            fragmentTransaction1.replace(R.id.selectionsContainer, destinationSelection);
                            fragmentTransaction1.commit();
                            num_clicked++;
                            bundle.putString("OriginCity", cityString);
                            bundle.putString("OriginState", stateString);
                            bundle.putString("OriginCountry", countryString);
                            bundle.putString("OriginCode", mapFinal.get(countryString));
                            ArrayList<String> countries2 = new ArrayList<String>();

                            fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                                @Override
                                public void onFragmentResumed(FragmentManager fm, Fragment f) {
                                    super.onFragmentResumed(fm, f);
                                    Map<String, String> countryMap = new HashMap<>();
                                    for (String iso : Locale.getISOCountries()) {
                                        Locale l = new Locale("", iso);
                                        countries2.add(l.getDisplayCountry());
                                        countryMap.put(l.getDisplayCountry(), iso);
                                    }
                                    mapFinal = countryMap;


                                            Collections.sort(countries2);
                                    ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(SelectionsPage.this,
                                            android.R.layout.simple_spinner_item, countries2);

                                    countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    AutoCompleteTextView spinner4 = findViewById(R.id.spinner4);
                                    if (spinner4 != null) {
                                        spinner4.setAdapter(countryAdapter);
                                        spinner4.setThreshold(1);
                                        spinner4.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                                                String country = spinner4.getText().toString();
                                                String code = countryMap.get(country);
                                                AutoCompleteTextView spinner41 = findViewById(R.id.spinner5);
                                                spinner41.setText("");
                                                AutoCompleteTextView spinner52 = findViewById(R.id.spinner6);
                                                spinner52.setText("");
                                                states = new ArrayList<String>();
                                                cities = new ArrayList<String>();


                                                ArrayList<JSONObject> statesJsons = new ArrayList<JSONObject>();
                                                try {
                                                    statesJsons = load(code);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                } catch (ParseException e) {
                                                    e.printStackTrace();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                for (JSONObject x: statesJsons){
                                                    try {
                                                        jsonList.add(x);
                                                        if (!states.contains(x.getString("state"))) {
                                                            states.add(x.getString("state"));
                                                        }
                                                        if (!cities.contains(x.getString("city"))) {
                                                            cities.add(x.getString("city"));
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                Collections.sort(cities);
                                                ArrayAdapter<String> countryAdapter2 = new ArrayAdapter<String>(SelectionsPage.this,
                                                        android.R.layout.simple_spinner_item, cities);

                                                countryAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                AutoCompleteTextView spinner2 = findViewById(R.id.spinner6);
                                                spinner2.setThreshold(2);//will start working from first character
                                                spinner2.setAdapter(countryAdapter2);

                                                Collections.sort(states);
                                                ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(SelectionsPage.this,
                                                        android.R.layout.simple_spinner_item, states);

                                                countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                AutoCompleteTextView spinner = findViewById(R.id.spinner5);
                                                spinner.setAdapter(countryAdapter);
                                                spinner.setThreshold(1);
                                                spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                                                        String state = spinner.getText().toString();
                                                        AutoCompleteTextView spinner52 = findViewById(R.id.spinner6);
                                                        spinner52.setText("");
                                                        cities = new ArrayList<String>();
                                                        for (int g = 0; g < jsonList.size(); g++) {
                                                            try {
                                                                if (jsonList.get(g).getString("state").matches(state) && !cities.contains(jsonList.get(g).getString("city"))) {
                                                                    cities.add(jsonList.get(g).getString("city"));
                                                                }
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                        Collections.sort(cities);
                                                        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(SelectionsPage.this,
                                                                android.R.layout.simple_spinner_item, cities);

                                                        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                        AutoCompleteTextView spinner = findViewById(R.id.spinner6);
                                                        spinner.setThreshold(1);//will start working from first character
                                                        spinner.setAdapter(countryAdapter);

                                                    }
                                                });
                                            }

                                        });
                                    }
                                }
                            }, true);

                        }
                        break;
                    case 2:

                        AutoCompleteTextView country2 = findViewById(R.id.spinner4);
                        AutoCompleteTextView state2 = findViewById(R.id.spinner5);
                        AutoCompleteTextView city2 = findViewById(R.id.spinner6);

                        String cityString2 = city2.getText().toString();
                        String stateString2 = state2.getText().toString();
                        String countryString2 = country2.getText().toString();

                        if (cityString2.matches("") || countryString2.matches("")) {
                            Toast.makeText(this, "Please enter an departure location", Toast.LENGTH_LONG).show();
                        } else {
                            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                            fragmentTransaction1.replace(R.id.selectionsContainer, dateSelection);
                            fragmentTransaction1.commit();
                            num_clicked++;
                            bundle.putString("DestinationCity", cityString2);
                            bundle.putString("DestinationState", stateString2);
                            bundle.putString("DestinationCountry", countryString2);
                            bundle.putString("DestinationCode", mapFinal.get(countryString2));

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
                        Log.d("budget", ""+bundle.getInt("budget"));
                        Log.d("toDay", ""+bundle.getInt("toDay"));
                        Log.d("toMonth", ""+bundle.getInt("toMonth"));
                        Log.d("toYear", ""+bundle.getInt("toYear"));
                        Log.d("fromDay", ""+bundle.getInt("fromDay"));
                        Log.d("fromMonth", ""+bundle.getInt("fromMonth"));
                        Log.d("fromYear", ""+bundle.getInt("fromYear"));
                        Log.d("city", ""+bundle.getString("OriginCity"));
                        Log.d("state", ""+bundle.getString("OriginState"));
                        Log.d("country", ""+bundle.getString("OriginCountry"));
                        Log.d("code", ""+bundle.getString("OriginCode"));
                        Log.d("city2", ""+bundle.getString("DestinationCity"));
                        Log.d("state2", ""+bundle.getString("DestinationState"));
                        Log.d("country2", ""+bundle.getString("DestinationCountry"));
                        Log.d("code2", ""+bundle.getString("DestinationCode"));
                        if (bundle.getInt("toDay") == 0 || bundle.getInt("toMonth") == 0 || bundle.getInt("toYear") == 0 || bundle.getInt("fromDay") == 0 || bundle.getInt("fromMonth") == 0 || bundle.getInt("fromYear") == 0) {
                            Toast.makeText(this, "Please enter departing and returning date", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(this, FlightTypeSelection.class);
                            intent.putExtra("bundle", bundle);
                            startActivity(intent, bundle);
                        }
                        break;
                }
        });
    }

    public ArrayList<JSONObject> load(String country) throws IOException, ParseException, JSONException {
        InputStream is = this.getAssets().open("airports.json");
        BufferedReader input = new BufferedReader(new InputStreamReader(is));
        ArrayList<JSONObject> places = new ArrayList<JSONObject>();
        if (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            JSONParser parser = new JSONParser();
            String firstLine = reader.readLine();
            String newLine = "";
            while(newLine != null){
                boolean isNull = false;
                newLine = reader.readLine();
                newLine = "{";
                for (int i = 0; i < 11; i++){
                    String line = reader.readLine();
                    if (line != null) {
                        newLine += line;
                    } else {
                        isNull = true;
                    }
                }
                if (isNull){
                    break;
                }
                if (newLine.charAt(newLine.length()-1) == ','){
                    newLine = newLine.substring(0,newLine.lastIndexOf(","));
                }
                JSONObject obj = new JSONObject(newLine);
                Iterator<String> key = obj.keys();
                String theKey = key.next();

                if (obj.getString("country").equals(country)) {
                    places.add(obj);
                }
            }
        }
        return places;
    }

    public void home(View view){
        Intent intent = new Intent(this, MainMenu.class);
        intent.putExtra("bundle", bundle);
        startActivity(intent, bundle);
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
