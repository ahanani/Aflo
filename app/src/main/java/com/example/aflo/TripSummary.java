package com.example.aflo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;

public class TripSummary extends AppCompatActivity {
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_summary);
        bundle = getIntent().getBundleExtra("bundle");
        int toDay = bundle.getInt("toDay");
        int toMonth = bundle.getInt("toMonth");
        int toYear = bundle.getInt("toYear");
        int fromDay = bundle.getInt("fromDay");
        int fromMonth = bundle.getInt("fromMonth");
        int fromYear = bundle.getInt("fromYear");
        String originCity = bundle.getString("OriginCity");
        String destCity = bundle.getString("DestinationCity");
        String hotelImageUrl = bundle.getString("hotelImage");
//        try {
//            InputStream in = new java.net.URL(hotelImageUrl).openStream();
//            Bitmap imageOfHotel = BitmapFactory.decodeStream(in);
//            ImageView imageView = findViewById(R.id.image);
//            imageView.setImageBitmap(imageOfHotel);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String hotelPrice = bundle.getString("hotelPrice");
        String hotelName = bundle.getString("hotelName");
        String hotelRating = bundle.getString("ratings");
        String hotelRatingsCount = bundle.getString("ratingsCount");
        int flightPrice = bundle.getInt("flightPrice");
        String fromTimeStart = bundle.getString("flightOutboundStartDatetime");
        String fromTimeEnd = bundle.getString("flightOutboundEndDatetime");
        String toTimeStart = bundle.getString("flightInboundStartDatetime");
        String toTimeEnd = bundle.getString("flightInboundEndDatetime");


//        String airportCode = bundle.getString("");
        Formatter fmt = new Formatter();
        String toM = fmt.format("%tb", Month.of(toMonth)).toString();
        Formatter fmt1 = new Formatter();
        String fromM = fmt1.format("%tb", Month.of(fromMonth)).toString();

        String content = "$" + flightPrice;
        TextView flightPriceview = findViewById(R.id.price);
        flightPriceview.setText(content);

        StringBuilder departure = new StringBuilder();
        departure.append("From: ").append(fromTimeStart).append("\n")
                .append("To: ").append(fromTimeEnd);
        TextView departureTextView = findViewById(R.id.departing_shortened_text);
        departureTextView.setText(departure);

        StringBuilder returning = new StringBuilder();
        returning.append("From: ").append(toTimeStart).append("\n")
                .append("To: ").append(toTimeEnd);
        TextView returningTextView = findViewById(R.id.returning_shortened_text);
        returningTextView.setText(returning);

        TextView title1Response = findViewById(R.id.title1Response);
        title1Response.setText(originCity);

        TextView title2Response = findViewById(R.id.title2Response);
        title2Response.setText(destCity);

        TextView title3Response = findViewById(R.id.title3Response);
        StringBuilder title3ResponseText = new StringBuilder();
        title3ResponseText.append(fromM).append(". ").append(fromDay).append(", ").append(fromYear)
                .append(" - ").append(toM).append(". ").append(toDay).append(", ").append(toYear);
        title3Response.setText(title3ResponseText);

        TextView hotelTitle = findViewById(R.id.hotel);
        hotelTitle.setText(hotelName);

        String priceContent = "$" + hotelPrice + "/night";
        TextView priceOfHotel = findViewById(R.id.price1);
        priceOfHotel.setText(priceContent);

        TextView total = findViewById(R.id.total);
        StringBuilder spentContent = new StringBuilder();
        spentContent.append("Total spent").append("\n").append("$").
                append(bundle.getInt("spentBudget"));
        total.setText(spentContent);

        String ratingDetailText = hotelRating + " (" + hotelRatingsCount + ")";
        TextView ratingDetail = findViewById(R.id.detail);
        ratingDetail.setText(ratingDetailText);



        DonutSection section1 = new DonutSection("section_1", Color.parseColor("#FFA500"),bundle.getInt("flightPrice"));

        DonutSection section2 = new DonutSection("section_2", Color.parseColor("#FFED00"), bundle.getInt("budget") - bundle.getInt("flightPrice"));

        DonutSection section3 = new DonutSection("section_3", Color.parseColor("#FFFFFF"), bundle.getInt("budget") - bundle.getInt("spentBudget"));

        DonutProgressView donut_view = findViewById(R.id.donut_view);
        donut_view.setCap(bundle.getInt("budget"));
        donut_view.setAnimationDurationMs(3000);
        donut_view.setStrokeWidth(70);
        List<DonutSection> list = new ArrayList<DonutSection>();
        list.add(section1);
        list.add(section2);
        list.add(section3);
        donut_view.submitData(list);

    }

    public void saveToDB(View view) {
        FirebaseUser user = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser());
        if (user.isAnonymous()) {
            Intent requireAuth = new Intent(this, RequireLogin.class);
            startActivity(requireAuth);
        } else {
            String uid = user.getUid();
            TripSummaryRecord tripRecord = new TripSummaryRecord(bundle);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Trips");

            DatabaseReference userTrips = myRef.child(uid);
            String id = userTrips.push().getKey();
            assert id != null;
            userTrips.child(id).setValue(tripRecord);
        }
    }

    public void home(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}