package com.example.aflo;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class FlightDepartingSelectionDetails extends Fragment implements ItemClickListener {

    RecyclerView recyclerView;
    View view;
    ItemClickListener itemClickListener;
    ViewGroup container;
    LayoutInflater inflater;
//    Bundle bundle;


    ArrayList<Integer> images = new ArrayList<>();
    //    String[] flights, prices, stops;
//    int[] images = {R.drawable.flight_logo_1, R.drawable.flight_logo_2, R.drawable.flight_logo_3, R.drawable.flight_logo_1,
//            R.drawable.flight_logo_1, R.drawable.flight_logo_2, R.drawable.flight_logo_3, R.drawable.flight_logo_2,
//            R.drawable.flight_logo_2, R.drawable.flight_logo_1, R.drawable.flight_logo_1};

    ConstraintLayout row;
    boolean open = false;
    ConstraintLayout previouslyOpenRow = null;

    ArrayList<String> flights = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();
    ArrayList<String> stops = new ArrayList<>();
    ArrayList<String> carriersList = new ArrayList<>();
    ArrayList<FlightQuote> listOfFlightQuotes = new ArrayList<>();
    HashMap<String, String> carriers = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        flights = getResources().getStringArray(R.array.listOfDepartingFlightTimes);
//        prices = getResources().getStringArray(R.array.listOfDepartingFlightPrices);
//        stops = getResources().getStringArray(R.array.listOfDepartingFlightStops);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");


        Log.d("FlightDepartingSelectionDetails", "Bundle received: " + bundle);
        Log.d("FlightDepartingSelectionDetails", "Bundle budget: " + bundle.getInt("budget"));
        Log.d("FlightDepartingSelectionDetails", "Bundle destination: " + bundle.getString("destination"));
        Log.d("FlightDepartingSelectionDetails", "Bundle fromYear: " + bundle.getInt("fromYear"));
        Log.d("FlightDepartingSelectionDetails", "Bundle fromMonth: " + bundle.getInt("fromMonth"));
        Log.d("FlightDepartingSelectionDetails", "Bundle fromDay: " + bundle.getInt("fromDay"));

        Log.d("FlightDepartingSelectionDetails", "Bundle origin: " + bundle.getString("origin"));
        Log.d("FlightDepartingSelectionDetails", "Bundle toYear: " + bundle.getInt("toYear"));
        Log.d("FlightDepartingSelectionDetails", "Bundle toMonth: " + bundle.getInt("toMonth"));
        Log.d("FlightDepartingSelectionDetails", "Bundle toDay: " + bundle.getInt("toDay"));

        Log.d("FlightDepartingSelectionDetails", "Bundle minFlightPrice: " + bundle.getInt("minFlightPrice"));
        Log.d("FlightDepartingSelectionDetails", "Bundle maxFlightPrice: " + bundle.getInt("maxFlightPrice"));






        String country = "CA";
        String currency = "CAD";
        String locale = "en-GB";
        String originPlace = "YVR";
//        String destinationPlace = "MIA";
        String destinationPlace = "YYZ";
        String outboundPartialDate = Integer.toString(bundle.getInt("fromYear")) + "-" + Integer.toString(bundle.getInt("fromMonth")) + "-" + Integer.toString(bundle.getInt("fromDay"));
//        String outboundPartialDate = "anytime";
//        String outboundPartialDate = "2022-12-16";
//        String outboundPartialDate = "2023-01-23";
        String inboundPartialDate = Integer.toString(bundle.getInt("toYear")) + "-" + Integer.toString(bundle.getInt("toMonth")) + "-" + Integer.toString(bundle.getInt("toDay"));
//        String inboundPartialDate = "anytime";
//        String inboundPartialDate = "2022-12-23";
//        String inboundPartialDate = "2023-01-29";
        AsyncTaskRunner runner = new AsyncTaskRunner();
        String flightURL = "https://partners.api.skyscanner.net/apiservices/browsequotes/v1.0/" + country + "/" + currency + "/" + locale + "/" + originPlace + "/" + destinationPlace +"/" + outboundPartialDate +"/" + inboundPartialDate + "?apikey=prtl6749387986743898559646983194";
        runner.execute(flightURL);




    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
//        this.bundle = savedInstanceState;

        itemClickListener = this;

        View view = inflater.inflate(R.layout.activity_flights, container, false);
        recyclerView = view.findViewById(R.id.rowViewFlight);
        this.view = view;




//        flights = getResources().getStringArray(R.array.listOfDepartingFlightTimes);


//        RowRecyclerViewDepartingFlights rowRecyclerView = new RowRecyclerViewDepartingFlights(getActivity(), flights, images, prices, stops);
//        rowRecyclerView.setClickListener(this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        recyclerView.setAdapter(rowRecyclerView);
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        row = view.findViewById(R.id.departing_flight_row);
        ViewGroup.LayoutParams params = row.getLayoutParams();
        if (open) {
            open = false;
            previouslyOpenRow = row;
            expandRow(row, params, position);
        } else {
            if (previouslyOpenRow != null) {
                shrinkRow(previouslyOpenRow, previouslyOpenRow.getLayoutParams());
                previouslyOpenRow = null;
            }
            open = true;
            shrinkRow(row, params);
        }
    }

    public void shrinkRow(ConstraintLayout row, ViewGroup.LayoutParams params) {
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        row.setLayoutParams(params);
        ImageView image = row.findViewById(R.id.flight_logo);
        ViewGroup.LayoutParams imageParams = image.getLayoutParams();
        imageParams.height = -2;
        imageParams.width = -2;
        image.setLayoutParams(imageParams);
        TextView seeDetails = row.findViewById(R.id.seeDetails_departing_flight);
        seeDetails.setVisibility(View.VISIBLE);
        TextView select = row.findViewById(R.id.select_departing_flight);
        TableLayout table = row.findViewById(R.id.features_departing_flight);
        table.removeAllViews();
        select.setVisibility(View.INVISIBLE);
    }

    public void expandRow(ConstraintLayout row, ViewGroup.LayoutParams params, int position) {
        params.height = 1200;
        row.setLayoutParams(params);
        ImageView image = row.findViewById(R.id.flight_logo);
        ViewGroup.LayoutParams imageParams = image.getLayoutParams();
        imageParams.height = 500;
        imageParams.width = 500;
        image.setLayoutParams(imageParams);
        TextView seeDetails = row.findViewById(R.id.seeDetails_departing_flight);
        seeDetails.setVisibility(View.INVISIBLE);
        TextView select = row.findViewById(R.id.select_departing_flight);
        select.setVisibility(View.VISIBLE);
        TableLayout table = row.findViewById(R.id.features_departing_flight);
        TableRow flightType = new TableRow(getContext());
        TextView flightTypeText = new TextView(getContext());
//        flightTypeText.setText("Air Canada - Economy");

        flightTypeText.setText(carriersList.get(position));


        flightTypeText.setTextColor(Color.BLACK);
        flightTypeText.setTextSize(18);
        flightType.addView(flightTypeText);
        table.addView(flightType);

//        TextView dateArrive = row.findViewById(R.id.flight);
//        bundle_flight_departing.putString("dateArrive", dateArrive.toString());
//        Intent intent = getActivity().getIntent();




//
//        Intent intent1 = new Intent(view.getContext(), FlightReturningSelection.class);
//
//        startActivity(intent1);

    }

    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            RowRecyclerViewDepartingFlights rowRecyclerView = new RowRecyclerViewDepartingFlights(getActivity(), flights, images, prices, stops);
//            rowRecyclerView.setClickListener(itemClickListener);
//            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//            recyclerView.setAdapter(rowRecyclerView);

        }

        @Override
        protected String doInBackground(String... strings) {
            RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, strings[0], null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("JSONObject response", response.toString());

                        JSONObject o1 = new JSONObject(response.toString());
                        JSONArray jsonarray = o1.getJSONArray("Quotes");
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            String parsedQuoteId = jsonobject.getString("QuoteId");
                            String parsedMinPrice = jsonobject.getString("MinPrice");
                            String parsedDirect = jsonobject.getString("Direct");
                            String parsedOutboundLeg = jsonobject.getString("OutboundLeg");
                            String parsedInboundLeg = jsonobject.getString("InboundLeg");
                            String parsedQuoteDateTime = jsonobject.getString("QuoteDateTime");

//                            Log.d("QuoteID from parsing JSONArray", parsedQuoteId);
//                            Log.d("MinPrice from parsing JSONArray", parsedMinPrice);
//                            Log.d("Direct from parsing JSONArray", parsedDirect);
//                            Log.d("OutboundLeg from parsing JSONArray", parsedOutboundLeg);
//                            Log.d("InboundLeg from parsing JSONArray", parsedInboundLeg);
//                            Log.d("QuoteDateTime from parsing JSONArray", parsedQuoteDateTime);


                            FlightQuote currentFlightQuote = new FlightQuote();
                            currentFlightQuote.setQuoteId(parsedQuoteId);
                            currentFlightQuote.setMinPrice(parsedMinPrice);
                            currentFlightQuote.setDirect(parsedDirect);
                            currentFlightQuote.setOutboundLeg(parsedOutboundLeg);
                            currentFlightQuote.setInboundLeg(parsedInboundLeg);
                            currentFlightQuote.setQuoteDateTime(parsedQuoteDateTime);


                            JSONObject o2 = new JSONObject(response.toString());
                            JSONArray jsonarray2 = o2.getJSONArray("Carriers");
                            Log.d("CARRIERS", jsonarray2.toString());
                            for (int j = 0; j < jsonarray2.length(); j++) {
                                JSONObject jsonobject2 = jsonarray2.getJSONObject(j);
//                                Log.d("jsonobject2", jsonobject2.toString());
//                                Log.d("CarrierID", jsonobject2.getString("CarrierId"));
//                                Log.d("Carrier Name", jsonobject2.getString("Name"));
                                carriers.put(jsonobject2.getString("CarrierId"), jsonobject2.getString("Name"));
                            }
                            String x  = jsonobject.getString("OutboundLeg");

                            try {
                                JSONObject jObject = new JSONObject(x);
                                JSONArray y = jObject.getJSONArray("CarrierIds");
//                                Log.d("yCarrier", y.get(0).toString());
                                String currentCarrierID = y.get(0).toString();
//                                currentFlightQuote.setCarrier(currentCarrierID);
                                String currentCarrierName = carriers.get(currentCarrierID);
                                currentFlightQuote.setCarrier(currentCarrierName);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            listOfFlightQuotes.add(currentFlightQuote);
                        }





                        printFlightQuotes();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                public ArrayList<FlightQuote> printFlightQuotes() {
//                    Log.d("List of flight quotes size", Integer.toString(listOfFlightQuotes.size()));
                    for (int i =0; i < listOfFlightQuotes.size(); i++) {
                        images.add(R.drawable.ic_baseline_airplanemode_active_24);
//                        Log.d("printFlightQuotes index", Integer.toString(i));
//                        Log.d("printFlightQuotes FlightQuote Object", listOfFlightQuotes.get(i).toString());
//                        Log.d("outbound leg to string", listOfFlightQuotes.get(i).getOutboundLeg());
                        String x  = listOfFlightQuotes.get(i).getOutboundLeg();
                        String z  = listOfFlightQuotes.get(i).getInboundLeg();
                        String currentCarrierName = listOfFlightQuotes.get(i).getCarrier();
                        carriersList.add(currentCarrierName);

                        String y = "";
                        String z1 = "";
                        try {
                            JSONObject jObject = new JSONObject(x);
                            y = jObject.getString("DepartureDate").split("T")[0];
                            JSONObject jObjectIn = new JSONObject(z);
                            z1 = jObject.getString("DepartureDate").split("T")[0];
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        flights.add(y);
                        flights.add(z1);
//                        Log.d("flight outbound leg", listOfFlightQuotes.get(i).OutboundLeg);
                        prices.add("$" + listOfFlightQuotes.get(i).MinPrice);
//                        Log.d("flight min price", listOfFlightQuotes.get(i).MinPrice);


//                        Log.d("flight direct", listOfFlightQuotes.get(i).Direct);
                        if (listOfFlightQuotes.get(i).Direct.equals("true")) {
                            stops.add("Direct");
                        }
                        else {
                            stops.add("Non-direct");
                        }

                    }
                    Log.d("Carrier List", carriersList.toString());
                    Log.d("Carrier List length", Integer.toString(carriersList.size()));
                    RowRecyclerViewDepartingFlights rowRecyclerView = new RowRecyclerViewDepartingFlights(getActivity(), flights, images, prices, stops);
                    rowRecyclerView.setClickListener(itemClickListener);
                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    recyclerView.setAdapter(rowRecyclerView);

                    return listOfFlightQuotes;
                };

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(FlightQuoteAPICall.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
            return null;
        }
    }


}