package com.example.aflo;

import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FlightPackage {
    private String uri;
    private String linkBody;
    private int price;
    private String outboundId;
    private String inboundId;
    private String deeplink;

    // Derived
    private String outboundStartDatetime;
    private String outboundEndDatetime;

    private String inboundStartDatetime;
    private String inboundEndDatetime;


    private int id;
    private RowRecyclerViewDepartingFlights.RowViewHolder holder;
    private boolean expanded;
    // Expanded
    private String outboundCarrier;
    private String outboundCity;
    private String outboundAirportCode;
    private int outboundSegments;
    private String inboundCarrier;
    private String inboundCity;
    private String inboundAirportCode;
    private int inboundSegments;
    private String cabinClass;

    public FlightPackage(String uri, String linkBody, int price,
                         String outboundId, String inboundId, String deeplink) {
        this.uri = uri;
        this.linkBody = linkBody;
        this.price = price;
        this.outboundId = outboundId;
        this.inboundId = inboundId;
        this.deeplink = deeplink;

        String[] outboundDT = parseDateTimeFromId(outboundId);
        this.outboundStartDatetime = outboundDT[0];
        this.outboundEndDatetime = outboundDT[1];

        String[] inboundDT = parseDateTimeFromId(inboundId);
        this.inboundStartDatetime = inboundDT[0];
        this.inboundEndDatetime = inboundDT[1];

        this.expanded = false;
    }

    public void update(String outboundCarrier, String outboundCity, String outboundAirportCode,
                       int outboundSegments, String inboundCarrier, String inboundCity,
                       String inboundAirportCode, int inboundSegments, String cabinClass) {
        setOutboundCarrier(outboundCarrier);
        setOutboundCity(outboundCity);
        setOutboundAirportCode(outboundAirportCode);
        setOutboundSegments(outboundSegments);
        setInboundCarrier(inboundCarrier);
        setInboundCity(inboundCity);
        setInboundAirportCode(inboundAirportCode);
        setInboundSegments(inboundSegments);
        setCabinClass(cabinClass);
    }

    private String[] parseDateTimeFromId(String legId) {
        String millenia = "20";
        String[] partition = legId.split("--");

        // Start
        String[] startPartition = partition[0].split("-");
        String startDateTimeStr = millenia + startPartition[startPartition.length - 1];

        // End
        String[] endPartition = partition[partition.length - 1].split("-");
        String endDateTimeStr = millenia + endPartition[endPartition.length - 1];
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm",
                    new Locale("en-US"));

            Date startDateTime = dateFormat.parse(startDateTimeStr);
            Date endDateTime = dateFormat.parse(endDateTimeStr);
            String startDateTimeParsed = startDateTime.toString();
            String endDateTimeParsed = endDateTime.toString();
            return new String[]{startDateTimeParsed, endDateTimeParsed};
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new String[]{startDateTimeStr, endDateTimeStr};
    }

    public String getUri() {
        return uri;
    }

    public String getLinkBody() {
        return linkBody;
    }

    public String getOutboundId() {
        return outboundId;
    }

    public String getInboundId() {
        return inboundId;
    }

    public String getOutboundStartDatetime() {
        return outboundStartDatetime;
    }

    public String getInboundStartDatetime() {
        return inboundStartDatetime;
    }

    public int getPrice() {
        return price;
    }

    public String getOutboundCarrier() {
        return outboundCarrier;
    }

    public String getOutboundCity() {
        return outboundCity;
    }

    public String getOutboundAirportCode() {
        return outboundAirportCode;
    }

    public String getInboundCarrier() {
        return inboundCarrier;
    }

    public String getInboundCity() {
        return inboundCity;
    }

    public String getInboundAirportCode() {
        return inboundAirportCode;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setLinkBody(String linkBody) {
        this.linkBody = linkBody;
    }

    public void setOutboundId(String outboundId) {
        this.outboundId = outboundId;
    }

    public void setInboundId(String inboundId) {
        this.inboundId = inboundId;
    }

    public void setOutboundStartDatetime(String outboundStartDatetime) {
        this.outboundStartDatetime = outboundStartDatetime;
    }

    public void setInboundStartDatetime(String inboundStartDatetime) {
        this.inboundStartDatetime = inboundStartDatetime;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOutboundCarrier(String outboundCarrier) {
        this.outboundCarrier = outboundCarrier;
    }

    public void setOutboundCity(String outboundCity) {
        this.outboundCity = outboundCity;
    }

    public void setOutboundAirportCode(String outboundAirportCode) {
        this.outboundAirportCode = outboundAirportCode;
    }

    public void setInboundCarrier(String inboundCarrier) {
        this.inboundCarrier = inboundCarrier;
    }

    public void setInboundCity(String inboundCity) {
        this.inboundCity = inboundCity;
    }

    public void setInboundAirportCode(String inboundAirportCode) {
        this.inboundAirportCode = inboundAirportCode;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public int getOutboundSegments() {
        return outboundSegments;
    }

    public void setOutboundSegments(int outboundSegments) {
        this.outboundSegments = outboundSegments;
    }

    public int getInboundSegments() {
        return inboundSegments;
    }

    public void setInboundSegments(int inboundSegments) {
        this.inboundSegments = inboundSegments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RowRecyclerViewDepartingFlights.RowViewHolder getHolder() {
        return holder;
    }

    public void setHolder(RowRecyclerViewDepartingFlights.RowViewHolder holder) {
        this.holder = holder;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getOutboundEndDatetime() {
        return outboundEndDatetime;
    }

    public void setOutboundEndDatetime(String outboundEndDatetime) {
        this.outboundEndDatetime = outboundEndDatetime;
    }

    public String getInboundEndDatetime() {
        return inboundEndDatetime;
    }

    public void setInboundEndDatetime(String inboundEndDatetime) {
        this.inboundEndDatetime = inboundEndDatetime;
    }

    @Override
    public String toString() {
        return "FlightPackage{" +
                "uri='" + uri + '\'' +
                ", linkBody='" + linkBody + '\'' +
                ", price=" + price +
                ", outboundId='" + outboundId + '\'' +
                ", inboundId='" + inboundId + '\'' +
                ", deeplink='" + deeplink + '\'' +
                ", outboundStartDatetime='" + outboundStartDatetime + '\'' +
                ", outboundEndDatetime='" + outboundEndDatetime + '\'' +
                ", inboundStartDatetime='" + inboundStartDatetime + '\'' +
                ", inboundEndDatetime='" + inboundEndDatetime + '\'' +
                ", id=" + id +
                ", holder=" + holder +
                ", expanded=" + expanded +
                ", outboundCarrier='" + outboundCarrier + '\'' +
                ", outboundCity='" + outboundCity + '\'' +
                ", outboundAirportCode='" + outboundAirportCode + '\'' +
                ", outboundSegments=" + outboundSegments +
                ", inboundCarrier='" + inboundCarrier + '\'' +
                ", inboundCity='" + inboundCity + '\'' +
                ", inboundAirportCode='" + inboundAirportCode + '\'' +
                ", inboundSegments=" + inboundSegments +
                ", cabinClass='" + cabinClass + '\'' +
                '}';
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("flightPrice", price);
        bundle.putString("flightOutboundStartDatetime", outboundStartDatetime);
        bundle.putString("flightOutboundEndDatetime", outboundEndDatetime);
        bundle.putString("flightInboundStartDatetime", inboundStartDatetime);
        bundle.putString("flightInboundEndDatetime", inboundEndDatetime);
        bundle.putString("flightLink", deeplink);
        return bundle;
    }
}
