package com.example.aflo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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
//        AsyncTaskRunner runner = new AsyncTaskRunner();
//        String flightURL = "https://partners.api.skyscanner.net/apiservices/browsequotes/v1.0/" + country + "/" + currency + "/" + locale + "/" + originPlace + "/" + destinationPlace +"/" + outboundPartialDate +"/" + inboundPartialDate + "?apikey=prtl6749387986743898559646983194";
        String flightURL = "https://partners.api.skyscanner.net/apiservices/pricing/v1.0";
        String API_KEY = "prtl6749387986743898559646983194";
//        runner.execute(flightURL);
        HashMap<String, String> reqDetails = new HashMap<>();
        reqDetails.put("country", "CA");
        reqDetails.put("currency", "CAD");
        reqDetails.put("locale", "en-US");
        reqDetails.put("locationSchema", "iata");
        reqDetails.put("originplace", "YVR");
        reqDetails.put("destinationplace", "YTO");
        reqDetails.put("outbounddate", "2022-11-25");
        reqDetails.put("inbounddate", "2022-11-28");
        reqDetails.put("cabinclass", "Economy");
        reqDetails.put("adults", "" + 1);
        reqDetails.put("apikey", API_KEY);
        reqDetails.put("url", flightURL);

        StartPollSessionTask startSession = new StartPollSessionTask();
        startSession.execute(reqDetails);
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


        TableLayout departing = row.findViewById(R.id.departing_table_layout);
        TableLayout returning = row.findViewById(R.id.returning_table_layout);
        departing.setVisibility(View.INVISIBLE);
        returning.setVisibility(View.INVISIBLE);
        TextView date = row.findViewById(R.id.flight);
//        date.setVisibility(View.VISIBLE);
        date.setVisibility(View.INVISIBLE);
        TextView visitSite = row.findViewById(R.id.visit_site_button);
        visitSite.setVisibility(View.INVISIBLE);


        // show again the info from the shrunk view
        TableLayout departingShrunk = row.findViewById(R.id.departing_shortened_summary_tablelayout);
        TableLayout returningShrunk = row.findViewById(R.id.returning_shortened_summary_tablelayout);
        departingShrunk.setVisibility(View.VISIBLE);
        returningShrunk.setVisibility(View.VISIBLE);


    }

    public void expandRow(ConstraintLayout row, ViewGroup.LayoutParams params, int position) {
        params.height = 1000;
        row.setLayoutParams(params);
        ImageView image = row.findViewById(R.id.flight_logo);
        ViewGroup.LayoutParams imageParams = image.getLayoutParams();
        imageParams.height = 150;
        imageParams.width = 150;
        image.setLayoutParams(imageParams);
        TextView seeDetails = row.findViewById(R.id.seeDetails_departing_flight);
        seeDetails.setVisibility(View.INVISIBLE);
        TextView select = row.findViewById(R.id.select_departing_flight);
        select.setVisibility(View.VISIBLE);


        TextView date = row.findViewById(R.id.flight);
        date.setVisibility(View.INVISIBLE);


        TextView visitSite = row.findViewById(R.id.visit_site_button);
        visitSite.setVisibility(View.VISIBLE);


        // making the TableViews containing the flight info visible
        TableLayout departing = row.findViewById(R.id.departing_table_layout);
        TableLayout returning = row.findViewById(R.id.returning_table_layout);
        departing.setVisibility(View.VISIBLE);
        returning.setVisibility(View.VISIBLE);

        // hide the info from the shrunk view
        TableLayout departingShrunk = row.findViewById(R.id.departing_shortened_summary_tablelayout);
        TableLayout returningShrunk = row.findViewById(R.id.returning_shortened_summary_tablelayout);
        departingShrunk.setVisibility(View.INVISIBLE);
        returningShrunk.setVisibility(View.INVISIBLE);



        TableLayout table = row.findViewById(R.id.features_departing_flight);
        TableRow flightType = new TableRow(getContext());
        TextView flightTypeText = new TextView(getContext());
//        flightTypeText.setText("Air Canada - Economy");

        TextView priceToSendToBundle = row.findViewById(R.id.price);
        Log.d("price to send to bundle", priceToSendToBundle.getText().toString().substring(1));

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        bundle.putString("flightPrice", priceToSendToBundle.getText().toString().substring(1));


//        flightTypeText.setText(carriersList.get(position));


//        flightTypeText.setTextColor(Color.BLACK);
//        flightTypeText.setTextSize(18);
//        flightType.addView(flightTypeText);
//        table.addView(flightType);

//        TextView dateArrive = row.findViewById(R.id.flight);
//        bundle_flight_departing.putString("dateArrive", dateArrive.toString());
//        Intent intent = getActivity().getIntent();




//
//        Intent intent1 = new Intent(view.getContext(), FlightReturningSelection.class);
//
//        startActivity(intent1);

    }

    private class StartPollSessionTask extends AsyncTask<HashMap<String, String>, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(HashMap<String, String>... hashMaps) {
            try {
                String url = hashMaps[0].remove("url");

                OkHttpClient client = new OkHttpClient();
                FormBody.Builder bodyBuilder = new FormBody.Builder();

                hashMaps[0].forEach(bodyBuilder::add);

//                Log.d("startPollingSession", body.toString());
                assert url != null;
                Request request = new Request.Builder()
                        .url(url)
                        .post(bodyBuilder.build())
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    JSONObject responseJSON = new JSONObject(response.body().string());
                    String pollSessionURL = response.header("location");
                    Log.d("OK", responseJSON.toString());
                    Log.d("OK", "Header: " + pollSessionURL);

                    JSONObject result = new JSONObject();
                    result.put("url", pollSessionURL);
                    result.put("apikey", hashMaps[0].get("apikey"));
                    result.put("response", responseJSON);
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                String url = jsonObject.getString("url");
                String apikey = jsonObject.getString("apikey");

                HashMap<String, String> request = new HashMap<>();
                request.put("url", url);
                request.put("apikey", apikey);
                request.put("sortType", "price");
                request.put("pageIndex", "0");
                request.put("pageSize", "5");

                PollSessionTask pollSession = new PollSessionTask();
                pollSession.execute(request);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class PollSessionTask extends AsyncTask<HashMap<String, String>, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(HashMap<String, String>... hashMaps) {
            HashMap<String, String> req = hashMaps[0];
            String url = req.remove("url");

            final ArrayList<String> reqParamsURL = new ArrayList<>();
            req.forEach((k, v) -> reqParamsURL.add(k + "=" + v));
            url += "?" + String.join("&", reqParamsURL);
            Log.d("PollSession", "URL: " + url);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try {
                Response response = client.newCall(request).execute();
                JSONObject responseJSON = new JSONObject(response.body().string());
                Log.d("PollSession", "Res: " + responseJSON.toString(4));

                JSONObject shortenedJSON = new JSONObject();
                shortenedJSON.put("SessionKey", responseJSON.getString("SessionKey"));
                shortenedJSON.put("Query", responseJSON.getJSONObject("Query"));
                shortenedJSON.put("Itineraries", responseJSON.getJSONArray("Itineraries"));
                Log.d("PollSession", "toPostExec: " + shortenedJSON.toString(4));
                return shortenedJSON;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public ArrayList<HashMap<String, String>> parseItinerary(JSONObject itinerary) {
            try {
                ArrayList<HashMap<String, String>> result = new ArrayList<>();

                JSONObject bookingDetailsLinkInfo = itinerary.getJSONObject("BookingDetailsLink");
                String uri = bookingDetailsLinkInfo.getString("Uri");
                String body = bookingDetailsLinkInfo.getString("Body");
                String outboundId = itinerary.getString("OutboundLegId");
                String inboundId = itinerary.getString("InboundLegId");

                JSONArray pricingOptions = itinerary.getJSONArray("PricingOptions");
                int pricingOptionsLength = pricingOptions.length();
                for (int i = 0; i < pricingOptionsLength; ++i) {
                    JSONObject option = pricingOptions.getJSONObject(i);

                    HashMap<String, String> booking = new HashMap<>();
                    booking.put("uri", uri);
                    booking.put("body", body);
                    booking.put("price", option.getString("Price"));
                    booking.put("deeplinkUrl", option.getString("DeeplinkUrl"));
                    booking.put("outboundlegid", outboundId);
                    booking.put("inboundlegid", inboundId);
                    result.add(booking);
                }
                return result;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                JSONArray itineraries = jsonObject.getJSONArray("Itineraries");
                int itinerariesLength = itineraries.length();

                ArrayList<HashMap<String, String>> allItineraries = new ArrayList<>();
                for (int i = 0; i < itinerariesLength; ++i) {
                    JSONObject itinerary = itineraries.getJSONObject(i);
                    Log.d("PollSessionTask", itinerary.toString(4));
                    allItineraries.addAll(Objects.requireNonNull(parseItinerary(itinerary)));
                }

                // LOG RESULT FOR NOW
                for (HashMap<String, String> it: allItineraries) {
                    Log.d("PollSessionTaskRES", it.toString());
                }
                // Test getting details for ones
                GetBookingDetailsTask getBookingDetails = new GetBookingDetailsTask();
                HashMap<String, String> test = allItineraries.get(0);
                test.put("apikey", "prtl6749387986743898559646983194");
                getBookingDetails.execute(test);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetBookingDetailsTask extends AsyncTask<HashMap<String, String>, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(HashMap<String, String>... hashMaps) {
            HashMap<String, String> req = hashMaps[0];
            String url = "https://partners.api.skyscanner.net";
            String endpointUri = req.get("uri");
            String apikey = req.get("apikey");

            String fullUrl = url + endpointUri + "?apikey=" + apikey;

            Log.d("GetBookingDetails", fullUrl);

            HashMap<String, String> reqBody = new HashMap<>();
            reqBody.put("OutboundLegId", req.get("outboundlegid"));
            reqBody.put("InboundLegId", req.get("inboundlegid"));

            FormBody.Builder reqBodyBuilder = new FormBody.Builder();
            reqBody.forEach(reqBodyBuilder::add);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(fullUrl)
                    .put(reqBodyBuilder.build())
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String location = response.header("location");

                Log.d("GetBookingDetails", location);
                JSONObject returnObject = new JSONObject();
                returnObject.put("url", location);
                returnObject.put("apikey", apikey);

                return returnObject;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                HashMap<String, String> req = new HashMap<>();
                req.put("url", jsonObject.getString("url"));
                req.put("apikey", jsonObject.getString("apikey"));

                PollBookingTask pollBookingTask = new PollBookingTask();
                pollBookingTask.execute(req);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class PollBookingTask extends AsyncTask<HashMap<String, String>, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(HashMap<String, String>... hashMaps) {
            HashMap<String, String> req = hashMaps[0];
            String fullUrl = req.get("url") + "?apikey=" + req.get("apikey");

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(fullUrl)
                    .get()
                    .build();
            try {
                Response response = client.newCall(request).execute();
                JSONObject responseJSON = new JSONObject(response.body().string());
                Log.d("PollBookingTask", responseJSON.toString(4));
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}