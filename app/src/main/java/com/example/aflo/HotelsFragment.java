package com.example.aflo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;


public class HotelsFragment extends Fragment implements ItemClickListener {

    ArrayList<String> titles;
    ArrayList<String> prices;
    ArrayList<String> ids;
    ArrayList<Bitmap> imagesOfHotels;
    ArrayList<String> urlOfHotelsImage;
    ArrayList<String> ratings;
    ArrayList<String> ratingsCount;
    ArrayList<String> amenitiesScreen;
    ArrayList<String> geoPoint;
    RecyclerView recyclerView;
    ViewGroup container;
    LayoutInflater inflater;
    Bundle bundle;
    View view;
    ItemClickListener itemClickListener;
    TextView hotelTitle;
    String checkIn;
    String checkOut;

    ConstraintLayout row;
    boolean open = false;
    ConstraintLayout previouslyOpenRow = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titles = new ArrayList<>();
        prices = new ArrayList<>();
        ids = new ArrayList<>();
        imagesOfHotels = new ArrayList<>();
        urlOfHotelsImage = new ArrayList<>();
        ratings = new ArrayList<>();
        ratingsCount = new ArrayList<>();
        amenitiesScreen = new ArrayList<>();
        geoPoint = new ArrayList<>();


        bundle = requireActivity().getIntent().getExtras().getBundle("bundle");
        int toDay = bundle.getInt("toDay");
        int toMonth = bundle.getInt("toMonth");
        int toYear = bundle.getInt("toYear");
        int fromDay = bundle.getInt("fromDay");
        int fromMonth = bundle.getInt("fromMonth");
        int fromYear = bundle.getInt("fromYear");
        checkIn = fromYear + "-" + fromMonth + "-" + fromDay;
        checkOut = toYear + "-" + toMonth + "-" + toDay;
        String city = bundle.getString("DestinationCity");
        AsyncTaskRunner runner = new AsyncTaskRunner();
        String urlForLocations = "https://tripadvisor16.p.rapidapi.com/api/v1/hotels/searchLocation?query=";
        runner.execute(urlForLocations + city);
        Log.d("Bundle toString", bundle.toString());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;

        itemClickListener = this;

        View view = inflater.inflate(R.layout.activity_hotels, container, false);
        recyclerView = view.findViewById(R.id.rowView);
        this.view = view;
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        row = view.findViewById(R.id.hotel_row);
        ViewGroup.LayoutParams params = row.getLayoutParams();
        if (open) {
            open = false;
            previouslyOpenRow = row;
            expandRow(row, params, position);
        } else {
            if (previouslyOpenRow != null) {
                collapseRow(previouslyOpenRow, previouslyOpenRow.getLayoutParams());
                previouslyOpenRow = null;
            }
            open = true;
            collapseRow(row, params);
        }
    }

    public void collapseRow(ConstraintLayout row, ViewGroup.LayoutParams params) {
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        row.setLayoutParams(params);
        ImageView image = row.findViewById(R.id.image);
        ViewGroup.LayoutParams imageParams = image.getLayoutParams();
        imageParams.height = -2;
        imageParams.width = -2;
        image.setLayoutParams(imageParams);
        TextView seeDetails = row.findViewById(R.id.seeDetails);
        seeDetails.setVisibility(View.VISIBLE);
        TextView select = row.findViewById(R.id.select);
        TextView visit = row.findViewById(R.id.visit);
        TableLayout table = row.findViewById(R.id.features);
        visit.setOnClickListener(null);
        table.removeAllViews();
        select.setVisibility(View.INVISIBLE);
        visit.setVisibility(View.INVISIBLE);
        amenitiesScreen.clear();
        geoPoint.clear();
        if (hotelTitle != null) {
            ViewGroup.LayoutParams hotelTitleParams = hotelTitle.getLayoutParams();
            hotelTitleParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            hotelTitle.setLayoutParams(hotelTitleParams);
        }
    }

    public void expandRow(ConstraintLayout row, ViewGroup.LayoutParams params, int position) {
        params.height = 1200;
        row.setLayoutParams(params);
        ImageView image = row.findViewById(R.id.image);
        ViewGroup.LayoutParams imageParams = image.getLayoutParams();
        imageParams.height = 500;
        imageParams.width = 500;
        image.setLayoutParams(imageParams);
        TextView seeDetails = row.findViewById(R.id.seeDetails);
        seeDetails.setVisibility(View.INVISIBLE);
        TextView select = row.findViewById(R.id.select);
        select.setVisibility(View.VISIBLE);
        select.setOnClickListener(v -> {
            bundle.putString("hotelImage", urlOfHotelsImage.get(position));
//            bundle.putString("hotelPrice", prices.get(position).substring(1));
            int hotelPrice = Integer.parseInt(prices.get(position).substring(1));
            bundle.putInt("hotelPrice", hotelPrice);
            bundle.putString("hotelName", titles.get(position));
            bundle.putString("ratings", ratings.get(position));
            int toDay = bundle.getInt("toDay");
            int toMonth = bundle.getInt("toMonth");
            int toYear = bundle.getInt("toYear");
            int fromDay = bundle.getInt("fromDay");
            int fromMonth = bundle.getInt("fromMonth");
            int fromYear = bundle.getInt("fromYear");
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                Date from = simpleDateFormat.parse(fromMonth+"/"+fromDay+"/"+fromYear);
                Date to = simpleDateFormat.parse(toMonth+"/"+toDay+"/"+toYear);

                assert to != null;
                assert from != null;
                long timeDiff = Math.abs(from.getTime() - to.getTime());
                long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
                bundle.putString("ratingsCount", ratingsCount.get(position));
                bundle.putInt("spentBudget", bundle.getInt("spentBudget") + (hotelPrice * (int) daysDiff));
                bundle.putInt("numberDays", (int) daysDiff);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(getContext(), TripComplete.class);
            intent.putExtra("bundle", bundle);
            startActivity(intent);

        });
        TextView visit = row.findViewById(R.id.visit);
        visit.setVisibility(View.VISIBLE);
        visit.setOnClickListener(v -> {
            String url1 = "geo:" + geoPoint.get(0) + "," + geoPoint.get(1) + "?q=" + titles.get(position);
            Uri gmmIntentUri = Uri.parse(url1);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
        AsyncTaskForHotelAmenities runner2 = new AsyncTaskForHotelAmenities();
        runner2.execute(String.valueOf(ids.get(position)));
        hotelTitle = previouslyOpenRow.findViewById(R.id.hotel);
        ViewGroup.LayoutParams hotelTitleParams = hotelTitle.getLayoutParams();
        hotelTitleParams.width = 380;
        hotelTitle.setLayoutParams(hotelTitleParams);

    }

    private class AsyncTaskRunner extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute (String result) {
            RowRecyclerView rowRecyclerView = new RowRecyclerView(getActivity(),
                    titles, imagesOfHotels, prices, ratings, ratingsCount);
            rowRecyclerView.setClickListener(itemClickListener);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(rowRecyclerView);
            view.findViewById(R.id.loading).setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            String appKey = "173f756567mshbc610ecc1c79d97p185285jsn76c9fdedd129";
            Request request = new Request.Builder()
                    .url(strings[0])
                    .get()
                    .addHeader("X-RapidAPI-Key", appKey)
                    .addHeader("X-RapidAPI-Host", "tripadvisor16.p.rapidapi.com")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                assert response.body() != null;
                JSONObject jsonResponse = new JSONObject(response.body().string());
                Log.d("Hotel Res", jsonResponse.toString());
                JSONArray array = jsonResponse.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject item = (JSONObject) array.getJSONObject(i);
                    String city = bundle.getString("DestinationCity");
                    String state = bundle.getString("DestinationState");
                    String country = bundle.getString("DestinationCountry");
                    Log.d("Hotel Res 1.5", item.toString());
                    if (item.get("secondaryText").equals(state + ", " + country) ||
                            item.get("secondaryText").equals(city + ", " + state + ", " + country)) {
                        int geoId = item.getInt("geoId");
                        OkHttpClient client2 = new OkHttpClient();
                        Request request2 = new Request.Builder()
                                .url("https://tripadvisor16.p.rapidapi.com/api/v1/hotels/" +
                                        "searchHotels?geoId=" + geoId + "&checkIn=" + checkIn + "&checkOut" +
                                        "=" + checkOut + "&pageNumber=1&currencyCode=USD")
                                .get()
                                .addHeader("X-RapidAPI-Key", appKey)
                                .addHeader("X-RapidAPI-Host", "tripadvisor16.p.rapidapi.com")
                                .build();
                        Response response2 = client2.newCall(request2).execute();
                        assert response2.body() != null;
                        JSONObject jsonResponse2 = new JSONObject(response2.body().string());
                        Log.d("Hotel Res 2", jsonResponse2.toString());
                        JSONObject data = jsonResponse2.getJSONObject("data");
                        JSONArray array2 = data.getJSONArray("data");
                        for (int j = 0; j < 10; j++) {
                            JSONObject hotels = (JSONObject) array2.get(j);
                            ids.add(hotels.getString("id"));
                            String title = hotels.getString("title");
                            if (title.charAt(0) == '1' | title.charAt(0) == '2' |
                                    title.charAt(0) == '3' | title.charAt(0) == '4' |
                                    title.charAt(0) == '5' | title.charAt(0) == '6' |
                                    title.charAt(0) == '7' | title.charAt(0) == '8' |
                                    title.charAt(0) == '9' | title.charAt(0) == '0' ) {
                                title = title.substring(3);
                            }
                            if (title.length() > 20) {
                                title = title.substring(0, 20) + "...";
                            }
                            titles.add(title);
                            prices.add(hotels.getString("priceForDisplay"));
                            JSONArray cardPhotos =  hotels.getJSONArray("cardPhotos");
                            JSONObject firstPhoto = cardPhotos.getJSONObject(0).getJSONObject("sizes");
                            String image = firstPhoto.getString("urlTemplate");
                            JSONObject ratingStuff = hotels.getJSONObject("bubbleRating");
                            ratings.add(ratingStuff.getString("rating"));
                            ratingsCount.add(ratingStuff.getString("count"));
                            image = image.replace("{width}", "500");
                            image = image.replace("{height}", "500");
                            urlOfHotelsImage.add(image);
                            try {
                                InputStream in=new java.net.URL(image).openStream();
                                imagesOfHotels.add(BitmapFactory.decodeStream(in));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class AsyncTaskForHotelAmenities extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute (String result) {
            TableLayout table = row.findViewById(R.id.features);
            for (int i = 0; i < amenitiesScreen.size(); i++) {
                TableRow feature = new TableRow(getContext());
                TextView featureText = new TextView(getContext());
                featureText.setText(amenitiesScreen.get(i));
                featureText.setTextColor(Color.BLACK);
                featureText.setTextSize(15);
                feature.addView(featureText);
                table.addView(feature);
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            String appKey = "173f756567mshbc610ecc1c79d97p185285jsn76c9fdedd129";
            String url = "https://tripadvisor16.p.rapidapi.com/api/v1/hotels/getHotelDetails?id=" + strings[0] + "&checkIn=" + checkIn + "&checkOut=" + checkOut;
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("X-RapidAPI-Key", appKey)
                    .addHeader("X-RapidAPI-Host", "tripadvisor16.p.rapidapi.com")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                assert response.body() != null;
                JSONObject jsonResponse = new JSONObject(response.body().string());
                JSONObject data1 = jsonResponse.getJSONObject("data");
                JSONArray amenities = data1.getJSONArray("amenitiesScreen");
                JSONObject geoP = data1.getJSONObject("geoPoint");
                geoPoint.add(geoP.getString("latitude"));
                geoPoint.add(geoP.getString("longitude"));
                if (amenities.length() < 8) {
                    for (int i = 0; i < amenities.length(); i++) {
                        JSONObject item = amenities.getJSONObject(i);
                        JSONArray content = item.getJSONArray("content");
                        amenitiesScreen.add("\u2022 " + content.getString(0));
                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        JSONObject item = amenities.getJSONObject(i);
                        JSONArray content = item.getJSONArray("content");
                        amenitiesScreen.add("\u2022 " + content.getString(0));
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}