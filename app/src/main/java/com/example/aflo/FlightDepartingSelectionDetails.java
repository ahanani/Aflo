package com.example.aflo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import java.util.Locale;
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

    ConstraintLayout row;
    boolean open = false;
    ConstraintLayout previouslyOpenRow = null;
    StartPollSessionTask startSession;
    PollSessionTask pollSession;
    GetBookingDetailsTask getBookingDetailsTask;
    PollBookingTask pollBookingTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        Log.d("FlightDepartingSelectionDetails", "Bundle maxFlightPrice: " + bundle.getInt("maxFlightPrice"));

        Log.d("FlightDepartingSelectionDetails", "Bundle originAirportCode: " + bundle.getString("originAirportCode"));
        Log.d("FlightDepartingSelectionDetails", "Bundle destinationAirportCode: " + bundle.getString("destinationAirportCode"));
        Log.d("FlightDepartingSelectionDetails", "Bundle flightType: " + bundle.getString("flightType"));


        String flightURL = "https://partners.api.skyscanner.net/apiservices/pricing/v1.0";
        String API_KEY = "prtl6749387986743898559646983194";
        Locale localize = new Locale("en", "US");

        String fmtFromYear = String.format(localize, "%02d",
                bundle.getInt("fromYear"));
        String fmtFromMonth = String.format(localize, "%02d",
                bundle.getInt("fromMonth"));
        String fmtFromDay = String.format(localize, "%02d",
                bundle.getInt("fromDay"));
        String fmtToYear = String.format(localize, "%02d",
                bundle.getInt("toYear"));
        String fmtToMonth = String.format(localize, "%02d",
                bundle.getInt("toMonth"));
        String fmtToDay = String.format(localize, "%02d",
                bundle.getInt("toDay"));


        HashMap<String, String> reqDetails = new HashMap<>();
        reqDetails.put("country", "CA");
        reqDetails.put("currency", "CAD");
        reqDetails.put("locale", "en-US");
        reqDetails.put("locationSchema", "iata");
        // customize these
        reqDetails.put("originplace", bundle.getString("originAirportCode"));
        reqDetails.put("destinationplace", bundle.getString("destinationAirportCode"));
        reqDetails.put("outbounddate",
                String.join("-", fmtFromYear, fmtFromMonth, fmtFromDay)
        );
        reqDetails.put("inbounddate",
                String.join("-", fmtToYear, fmtToMonth, fmtToDay)
        );
        reqDetails.put("cabinclass", bundle.getString("flightType"));
        //
        reqDetails.put("adults", "" + 1);
        reqDetails.put("apikey", API_KEY);
        reqDetails.put("url", flightURL);

        startSession = new StartPollSessionTask();
        startSession.execute(reqDetails);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;

        itemClickListener = this;

        View view = inflater.inflate(R.layout.activity_flights, container, false);
        recyclerView = view.findViewById(R.id.rowViewFlight);
        this.view = view;
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

        TextView priceToSendToBundle = row.findViewById(R.id.price);
        Log.d("price to send to bundle", priceToSendToBundle.getText().toString().substring(1));
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        FlightPackage selectedFlightPackage = (
                (RowRecyclerViewDepartingFlights) Objects.requireNonNull(recyclerView.getAdapter())
        ).flightPackages.get(position);
        bundle.putAll(selectedFlightPackage.toBundle());

        if (!selectedFlightPackage.isExpanded()) {
            getBookingDetailsTask = new GetBookingDetailsTask();
            getBookingDetailsTask.execute(selectedFlightPackage);
        }
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

                pollSession = new PollSessionTask();
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
                boolean statusPending = true;
                JSONObject responseJSON;
                do {
                    Response response = client.newCall(request).execute();
                    String responseStr = response.body().string();
                    Log.d("PollSession", "Raw: " + responseStr);
                    responseJSON = new JSONObject(responseStr);
                    statusPending = !responseJSON.getString("Status").equals("UpdatesComplete");
                    SystemClock.sleep(2000);
                } while (statusPending);

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

        public ArrayList<FlightPackage> parseItinerary(JSONObject itinerary) {
            try {
                ArrayList<FlightPackage> result = new ArrayList<>();

                JSONObject bookingDetailsLinkInfo = itinerary.getJSONObject("BookingDetailsLink");
                String uri = bookingDetailsLinkInfo.getString("Uri");
                String body = bookingDetailsLinkInfo.getString("Body");
                String outboundId = itinerary.getString("OutboundLegId");
                String inboundId = itinerary.getString("InboundLegId");

                JSONArray pricingOptions = itinerary.getJSONArray("PricingOptions");
                int pricingOptionsLength = pricingOptions.length();
                for (int i = 0; i < pricingOptionsLength; ++i) {
                    JSONObject option = pricingOptions.getJSONObject(i);

                    result.add(new FlightPackage(uri, body, option.getInt("Price"),
                            outboundId, inboundId, option.getString("DeeplinkUrl")
                    ));

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

                ArrayList<FlightPackage> flightPackages = new ArrayList<>();
                for (int i = 0; i < itinerariesLength; ++i) {
                    JSONObject itinerary = itineraries.getJSONObject(i);
                    Log.d("PollSessionTask", itinerary.toString(4));
                    flightPackages.addAll(parseItinerary(itinerary));
                }

                requireActivity().findViewById(R.id.loading).setVisibility(View.GONE);

                FragmentContainerView fragmentContainerView = requireActivity().findViewById(R.id.mainFragment);
                fragmentContainerView.setVisibility(View.VISIBLE);

                RowRecyclerViewDepartingFlights rowRecyclerView =
                        new RowRecyclerViewDepartingFlights(getActivity(), flightPackages);
                rowRecyclerView.setClickListener(itemClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                recyclerView.setAdapter(rowRecyclerView);

                // LOG RESULT
                for (FlightPackage it: flightPackages) {
                    Log.d("PollSessionTaskRES", it.toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetBookingDetailsTask extends AsyncTask<FlightPackage, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(FlightPackage... flightPackages) {
            FlightPackage req = flightPackages[0];
            Log.d("GetBookingDetails", "req: " + req.toString());

            String url = "https://partners.api.skyscanner.net";
            String endpointUri = req.getUri();
            String apikey = "prtl6749387986743898559646983194";

            String fullUrl = url + endpointUri + "?apikey=" + apikey;

            Log.d("GetBookingDetails", fullUrl);

            HashMap<String, String> reqBody = new HashMap<>();
            reqBody.put("OutboundLegId", req.getOutboundId());
            reqBody.put("InboundLegId", req.getInboundId());
            Log.d("GetBookingDetails", reqBody.toString());

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
                Log.d("GetBookingDetails", response.body().string());
                Log.d("GetBookingDetails", location);
                JSONObject returnObject = new JSONObject();
                returnObject.put("url", location);
                returnObject.put("apikey", apikey);
                returnObject.put("flightPackageId", req.getId());

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
                req.put("flightPackageId", jsonObject.getString("flightPackageId"));

                pollBookingTask = new PollBookingTask();
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
                responseJSON.put("flightPackageId", req.get("flightPackageId"));
                return responseJSON;
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                HashMap<String, HashMap<String, String>> places =
                        parseCodeArray(jsonObject.getJSONArray("Places"));
                HashMap<String, HashMap<String, String>> carriers =
                        parseCodeArray(jsonObject.getJSONArray("Carriers"));
                HashMap<String, String> parsedSegments = parseSegments(
                        jsonObject.getJSONArray("Segments"), places, carriers);

                JSONObject originalQuery = jsonObject.getJSONObject("Query");

                HashMap<String, String> outboundPlace = Objects.requireNonNull(places.get(
                        originalQuery.getString("OriginPlace")));
                String outboundAirportCode = outboundPlace.get("Code");
                String outboundPlaceName = outboundPlace.get("Name");

                HashMap<String, String> inboundPlace = Objects.requireNonNull(places.get(
                        originalQuery.getString("DestinationPlace")));
                String inboundAirportCode = inboundPlace.get("Code");
                String inboundPlaceName = inboundPlace.get("Name");
                String cabinClass = originalQuery.getString("CabinClass");

                int flightPackageId = jsonObject.getInt("flightPackageId");
                RowRecyclerViewDepartingFlights recycler = (RowRecyclerViewDepartingFlights)
                        Objects.requireNonNull(recyclerView.getAdapter());
                FlightPackage selectedPackage = recycler.flightPackages.get(flightPackageId);
                Log.d("PollBookingTask", "FLIGHT: " + selectedPackage.toString());

                selectedPackage.update(parsedSegments.get("outboundCarrier"),
                        outboundPlaceName, outboundAirportCode,
                        Integer.parseInt(Objects.requireNonNull(parsedSegments.get("outboundNum"))),
                        parsedSegments.get("inboundCarrier"), inboundPlaceName, inboundAirportCode,
                        Integer.parseInt(Objects.requireNonNull(parsedSegments.get("inboundNum"))),
                        cabinClass
                        );

                recycler.updateView(selectedPackage.getHolder(), selectedPackage.getId());


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        HashMap<String, HashMap<String, String>> parseCodeArray(JSONArray jsonArr) throws JSONException {
            int len = jsonArr.length();
            HashMap<String, HashMap<String, String>> idToValsMap = new HashMap<>();
            for (int i = 0; i < len; ++i) {
                JSONObject idJSON = jsonArr.getJSONObject(i);
                HashMap<String, String> codeAndNameMap = new HashMap<>();
                codeAndNameMap.put("Code", idJSON.getString("Code"));
                codeAndNameMap.put("Name", idJSON.getString("Name"));
                idToValsMap.put(idJSON.getString("Id"), codeAndNameMap);
            }
            return idToValsMap;
        }

        HashMap<String, String> parseSegments(JSONArray segments,
                                              HashMap<String, HashMap<String, String>> places,
                                              HashMap<String, HashMap<String, String>> carriers)
                throws JSONException {
            HashMap<String, String> parsed = new HashMap<>();
            int len = segments.length();

            int numOutbound = 0;
            for (int i = 0; i < len; ++i) {
                JSONObject segment = segments.getJSONObject(i);
                if (segment.getString("Directionality").equalsIgnoreCase("Outbound")) {
                    numOutbound++;
                } else {
                    break;
                }
            }
            parsed.put("outboundNum", "" + numOutbound);
            parsed.put("inboundNum", "" + (len - numOutbound));

            JSONObject first = segments.getJSONObject(0);
            parsed.put("outboundCarrier", Objects.requireNonNull(
                    carriers.get(first.getString("Carrier"))).get("Name"));

            JSONObject firstInbound = segments.getJSONObject(numOutbound);
            parsed.put("inboundCarrier", Objects.requireNonNull(
                    carriers.get(firstInbound.getString("Carrier"))).get("Name"));
            return parsed;
        }


    }

    @Override
    public void onStop() {
        super.onStop();

        if (startSession != null) {
            startSession.cancel(true);
        }

        if (pollSession != null) {
            pollSession.cancel(true);
        }

        if (getBookingDetailsTask != null) {
            getBookingDetailsTask.cancel(true);
        }

        if (pollBookingTask != null) {
            pollBookingTask.cancel(true);
        }
    }
}