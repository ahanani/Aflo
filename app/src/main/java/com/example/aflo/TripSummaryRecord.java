package com.example.aflo;

import android.os.Bundle;

public class TripSummaryRecord {
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

    public TripSummaryRecord(int budget, int spentBudget,
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

    public TripSummaryRecord(Bundle bundle) {
        this(bundle.getInt("budget"), bundle.getInt("spentBudget"),
                bundle.getInt("fromYear"), bundle.getInt("fromMonth"), bundle.getInt("fromDay"), bundle.getInt("toYear"), bundle.getInt("toMonth"), bundle.getInt("toDay"),
                bundle.getString("OriginCountry"), bundle.getString("OriginState"), bundle.getString("OriginCity"), bundle.getString("OriginCode"), bundle.getString("originAirportCode"),
                bundle.getString("DestinationCountry"), bundle.getString("DestinationState"), bundle.getString("DestinationCity"), bundle.getString("DestinationCode"), bundle.getString("destinationAirportCode"),
                bundle.getInt("flightPrice"), bundle.getString("flightOutboundStartDatetime"), bundle.getString("flightOutboundEndDatetime"),
                bundle.getString("flightInboundStartDatetime"), bundle.getString("flightInboundEndDatetime"), bundle.getString("flightType"), bundle.getString("flightLink"),
                bundle.getInt("hotelPrice"), bundle.getInt("numberDays"), bundle.getString("hotelName"), bundle.getString("hotelImage"), bundle.getString("ratings"), bundle.getString("ratingsCount")
        );
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getSpentBudget() {
        return spentBudget;
    }

    public void setSpentBudget(int spentBudget) {
        this.spentBudget = spentBudget;
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(int fromMonth) {
        this.fromMonth = fromMonth;
    }

    public int getFromDay() {
        return fromDay;
    }

    public void setFromDay(int fromDay) {
        this.fromDay = fromDay;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public int getToMonth() {
        return toMonth;
    }

    public void setToMonth(int toMonth) {
        this.toMonth = toMonth;
    }

    public int getToDay() {
        return toDay;
    }

    public void setToDay(int toDay) {
        this.toDay = toDay;
    }

    public String getOriginCountry() {
        return OriginCountry;
    }

    public void setOriginCountry(String originCountry) {
        OriginCountry = originCountry;
    }

    public String getOriginState() {
        return OriginState;
    }

    public void setOriginState(String originState) {
        OriginState = originState;
    }

    public String getOriginCity() {
        return OriginCity;
    }

    public void setOriginCity(String originCity) {
        OriginCity = originCity;
    }

    public String getOriginCode() {
        return OriginCode;
    }

    public void setOriginCode(String originCode) {
        OriginCode = originCode;
    }

    public String getOriginAirportCode() {
        return originAirportCode;
    }

    public void setOriginAirportCode(String originAirportCode) {
        this.originAirportCode = originAirportCode;
    }

    public String getDestinationCountry() {
        return DestinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        DestinationCountry = destinationCountry;
    }

    public String getDestinationState() {
        return DestinationState;
    }

    public void setDestinationState(String destinationState) {
        DestinationState = destinationState;
    }

    public String getDestinationCity() {
        return DestinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        DestinationCity = destinationCity;
    }

    public String getDestinationCode() {
        return DestinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        DestinationCode = destinationCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public int getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(int flightPrice) {
        this.flightPrice = flightPrice;
    }

    public String getFlightOutboundStartDatetime() {
        return flightOutboundStartDatetime;
    }

    public void setFlightOutboundStartDatetime(String flightOutboundStartDatetime) {
        this.flightOutboundStartDatetime = flightOutboundStartDatetime;
    }

    public String getFlightOutboundEndDatetime() {
        return flightOutboundEndDatetime;
    }

    public void setFlightOutboundEndDatetime(String flightOutboundEndDatetime) {
        this.flightOutboundEndDatetime = flightOutboundEndDatetime;
    }

    public String getFlightInboundStartDatetime() {
        return flightInboundStartDatetime;
    }

    public void setFlightInboundStartDatetime(String flightInboundStartDatetime) {
        this.flightInboundStartDatetime = flightInboundStartDatetime;
    }

    public String getFlightInboundEndDatetime() {
        return flightInboundEndDatetime;
    }

    public void setFlightInboundEndDatetime(String flightInboundEndDatetime) {
        this.flightInboundEndDatetime = flightInboundEndDatetime;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public String getFlightLink() {
        return flightLink;
    }

    public void setFlightLink(String flightLink) {
        this.flightLink = flightLink;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(String ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    @Override
    public String toString() {
        return "TripSummaryRecord{" +
                "budget=" + budget +
                ", spentBudget=" + spentBudget +
                ", fromYear=" + fromYear +
                ", fromMonth=" + fromMonth +
                ", fromDay=" + fromDay +
                ", toYear=" + toYear +
                ", toMonth=" + toMonth +
                ", toDay=" + toDay +
                ", OriginCountry='" + OriginCountry + '\'' +
                ", OriginState='" + OriginState + '\'' +
                ", OriginCity='" + OriginCity + '\'' +
                ", OriginCode='" + OriginCode + '\'' +
                ", originAirportCode='" + originAirportCode + '\'' +
                ", DestinationCountry='" + DestinationCountry + '\'' +
                ", DestinationState='" + DestinationState + '\'' +
                ", DestinationCity='" + DestinationCity + '\'' +
                ", DestinationCode='" + DestinationCode + '\'' +
                ", destinationAirportCode='" + destinationAirportCode + '\'' +
                ", flightPrice=" + flightPrice +
                ", flightOutboundStartDatetime='" + flightOutboundStartDatetime + '\'' +
                ", flightOutboundEndDatetime='" + flightOutboundEndDatetime + '\'' +
                ", flightInboundStartDatetime='" + flightInboundStartDatetime + '\'' +
                ", flightInboundEndDatetime='" + flightInboundEndDatetime + '\'' +
                ", flightType='" + flightType + '\'' +
                ", flightLink='" + flightLink + '\'' +
                ", hotelPrice=" + hotelPrice +
                ", numberDays=" + numberDays +
                ", hotelName='" + hotelName + '\'' +
                ", hotelImage='" + hotelImage + '\'' +
                ", ratings='" + ratings + '\'' +
                ", ratingsCount='" + ratingsCount + '\'' +
                '}';
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();

        bundle.putInt("budget", budget);
        bundle.putInt("spentBudget", spentBudget);
        bundle.putInt("fromYear", fromYear);
        bundle.putInt("fromMonth", fromMonth);
        bundle.putInt("fromDay", fromDay);
        bundle.putInt("toYear", toYear);
        bundle.putInt("toMonth", toMonth);
        bundle.putInt("toDay", toDay);
        bundle.putString("OriginCountry", OriginCountry);
        bundle.putString("OriginState", OriginState);
        bundle.putString("OriginCity", OriginCity);
        bundle.putString("OriginCode", OriginCode);
        bundle.putString("originAirportCode", originAirportCode);
        bundle.putString("DestinationCountry", DestinationCountry);
        bundle.putString("DestinationState", DestinationState);
        bundle.putString("DestinationCity", DestinationCity);
        bundle.putString("DestinationCode", DestinationCode);
        bundle.putString("destinationAirportCode", destinationAirportCode);
        bundle.putInt("flightPrice", flightPrice);
        bundle.putString("flightOutboundStartDatetime", flightOutboundStartDatetime);
        bundle.putString("flightOutboundEndDatetime", flightOutboundEndDatetime);
        bundle.putString("flightInboundStartDatetime", flightInboundStartDatetime);
        bundle.putString("flightInboundEndDatetime", flightInboundEndDatetime);
        bundle.putString("flightType", flightType);
        bundle.putString("flightLink", flightLink);
        bundle.putInt("hotelPrice", hotelPrice);
        bundle.putInt("numberDays", numberDays);
        bundle.putString("hotelName", hotelName);
        bundle.putString("hotelImage", hotelImage);
        bundle.putString("ratings", ratings);
        bundle.putString("ratingsCount", ratingsCount);

        return bundle;
    }
}
