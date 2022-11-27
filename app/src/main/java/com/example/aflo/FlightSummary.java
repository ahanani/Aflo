package com.example.aflo;

import android.os.Bundle;

public class FlightSummary {
    private int budget;
    private int spentBudget;

    private int fromYear;
    private int fromMonth;
    private int fromDay;

    private int toYear;
    private int toMonth;
    private int toDay;

    private String OriginCountry;
    private String OriginState;
    private String OriginCity;
    private String OriginCode;
    private String originAirportCode;

    private String DestinationCountry;
    private String DestinationState;
    private String DestinationCity;
    private String DestinationCode;
    private String destinationAirportCode;

    private int flightPrice;
    private String flightOutboundStartDatetime;
    private String flightOutboundEndDatetime;
    private String flightInboundStartDatetime;
    private String flightInboundEndDatetime;
    private String flightType;
    private String flightLink;

    private int hotelPrice;
    private int numberDays;
    private String hotelName;
    private String hotelImage;
    private String ratings;
    private String ratingsCount;

    public FlightSummary(int budget, int spentBudget,
                         int fromYear, int fromMonth, int fromDay, int toYear, int toMonth, int toDay,
                         String originCountry, String originState, String originCity, String originCode, String originAirportCode,
                         String destinationCountry, String destinationState, String destinationCity, String destinationCode, String destinationAirportCode,
                         int flightPrice, String flightOutboundStartDatetime, String flightOutboundEndDatetime,
                         String flightInboundStartDatetime, String flightInboundEndDatetime, String flightType, String flightLink,
                         int hotelPrice, int numberDays, String hotelName, String hotelImage, String ratings, String ratingsCount) {
        this.budget = budget;
        this.spentBudget = spentBudget;
        this.fromYear = fromYear;
        this.fromMonth = fromMonth;
        this.fromDay = fromDay;
        this.toYear = toYear;
        this.toMonth = toMonth;
        this.toDay = toDay;
        OriginCountry = originCountry;
        OriginState = originState;
        OriginCity = originCity;
        OriginCode = originCode;
        this.originAirportCode = originAirportCode;
        DestinationCountry = destinationCountry;
        DestinationState = destinationState;
        DestinationCity = destinationCity;
        DestinationCode = destinationCode;
        this.destinationAirportCode = destinationAirportCode;
        this.flightPrice = flightPrice;
        this.flightOutboundStartDatetime = flightOutboundStartDatetime;
        this.flightOutboundEndDatetime = flightOutboundEndDatetime;
        this.flightInboundStartDatetime = flightInboundStartDatetime;
        this.flightInboundEndDatetime = flightInboundEndDatetime;
        this.flightType = flightType;
        this.flightLink = flightLink;
        this.hotelPrice = hotelPrice;
        this.numberDays = numberDays;
        this.hotelName = hotelName;
        this.hotelImage = hotelImage;
        this.ratings = ratings;
        this.ratingsCount = ratingsCount;
    }

    public FlightSummary(Bundle bundle) {
        this(bundle.getInt("budget"), bundle.getInt("spentBudget"),
                bundle.getInt("fromYear"), bundle.getInt("fromMonth"), bundle.getInt("fromDay"), bundle.getInt("toYear"), bundle.getInt("toMonth"), bundle.getInt("toDay"),
                bundle.getString("originCountry"), bundle.getString("originState"), bundle.getString("originCity"), bundle.getString("originCode"), bundle.getString("originAirportCode"),
                bundle.getString("destinationCountry"), bundle.getString("destinationState"), bundle.getString("destinationCity"), bundle.getString("destinationCode"), bundle.getString("destinationAirportCode"),
                bundle.getInt("flightPrice"), bundle.getString("flightOutboundStartDatetime"), bundle.getString("flightOutboundEndDatetime"),
                bundle.getString("flightInboundStartDatetime"), bundle.getString("flightInboundEndDatetime"), bundle.getString("flightType"), bundle.getString("flightLink"),
                bundle.getInt("hotelPrice"), bundle.getInt("numberDays"), bundle.getString("hotelName"), bundle.getString("hotelImage"), bundle.getString("ratings"), bundle.getString("ratingsCount")
        );
    }
}
