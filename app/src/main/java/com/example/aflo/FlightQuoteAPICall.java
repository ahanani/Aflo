//package com.example.aflo;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
//public class FlightQuoteAPICall extends AppCompatActivity{
//
//    ArrayList<FlightQuote> listOfFlightQuotes = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        String country = "CA";
//        String currency = "CAD";
//        String locale = "en-GB";
//        String originPlace = "YVR";
//        String destinationPlace = "MIA";
//        String outboundPartialDate = "anytime";
//        String inboundPartialDate = "anytime";
//        AsyncTaskRunner runner = new AsyncTaskRunner();
//        String flightURL = "https://partners.api.skyscanner.net/apiservices/browsequotes/v1.0/" + country + "/" + currency + "/" + locale + "/" + originPlace + "/" + destinationPlace +"/" + outboundPartialDate +"/" + inboundPartialDate + "?apikey=prtl6749387986743898559646983194";
//        runner.execute(flightURL);
//    }
//
//
//    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            RequestQueue queue = Volley.newRequestQueue(FlightQuoteAPICall.this);
//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, strings[0], null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    try {
//                        Log.d("JSONObject response", response.toString());
//
//                        JSONObject o1 = new JSONObject(response.toString());
//                        JSONArray jsonarray = o1.getJSONArray("Quotes");
//                        for (int i = 0; i < jsonarray.length(); i++) {
//                            JSONObject jsonobject = jsonarray.getJSONObject(i);
//                            String parsedQuoteId = jsonobject.getString("QuoteId");
//                            String parsedMinPrice = jsonobject.getString("MinPrice");
//                            String parsedDirect = jsonobject.getString("Direct");
//                            String parsedOutboundLeg = jsonobject.getString("OutboundLeg");
//                            String parsedInboundLeg = jsonobject.getString("InboundLeg");
//                            String parsedQuoteDateTime = jsonobject.getString("QuoteDateTime");
//
//                            Log.d("QuoteID from parsing JSONArray", parsedQuoteId);
//                            Log.d("MinPrice from parsing JSONArray", parsedMinPrice);
//                            Log.d("Direct from parsing JSONArray", parsedDirect);
//                            Log.d("OutboundLeg from parsing JSONArray", parsedOutboundLeg);
//                            Log.d("InboundLeg from parsing JSONArray", parsedInboundLeg);
//                            Log.d("QuoteDateTime from parsing JSONArray", parsedQuoteDateTime);
//
//
//                            FlightQuote currentFlightQuote = new FlightQuote();
//                            currentFlightQuote.setQuoteId(parsedQuoteId);
//                            currentFlightQuote.setMinPrice(parsedMinPrice);
//                            currentFlightQuote.setDirect(parsedDirect);
//                            currentFlightQuote.setOutboundLeg(parsedOutboundLeg);
//                            currentFlightQuote.setInboundLeg(parsedInboundLeg);
//                            currentFlightQuote.setQuoteDateTime(parsedQuoteDateTime);
//
//                            listOfFlightQuotes.add(currentFlightQuote);
//                        }
//
//                        printFlightQuotes();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                public ArrayList<FlightQuote> printFlightQuotes() {
//                    Log.d("List of flight quotes size", Integer.toString(listOfFlightQuotes.size()));
//                    for (int i =0; i < listOfFlightQuotes.size(); i++) {
//                        Log.d("printFlightQuotes index", Integer.toString(i));
//                        Log.d("printFlightQuotes FlightQuote Object", listOfFlightQuotes.get(i).toString());
//                    }
//                    return listOfFlightQuotes;
//                };
//
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(FlightQuoteAPICall.this, error.toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            queue.add(request);
//            return null;
//        }
//    }
//
//
//
//
//    }
//
//
//
//
//
//
