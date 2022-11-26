package com.example.aflo;

public class FlightPackage {
    private String uri;
    private String linkBody;
    private int price;
    private String outboundId;
    private String inboundId;
    private String deeplink;

    // Derived
    private String outboundDate;
    private String outboundTime;
    private String inboundDate;
    private String inboundTime;

    // Expanded
    private String outboundCarrier;
    private String outboundCity;
    private String outboundAirportCode;
    private int outboundSegments;
    private String inboundCarrier;
    private String inboundCity;
    private String inboundAirportCode;
    private int inboundSegments;

    public FlightPackage(String uri, String linkBody, int price,
                         String outboundId, String inboundId, String deeplink) {
        this.uri = uri;
        this.linkBody = linkBody;
        this.price = price;
        this.outboundId = outboundId;
        this.inboundId = inboundId;
        this.deeplink = deeplink;
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

    public String getOutboundDate() {
        return outboundDate;
    }

    public String getOutboundTime() {
        return outboundTime;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public String getInboundTime() {
        return inboundTime;
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

    public void setOutboundDate(String outboundDate) {
        this.outboundDate = outboundDate;
    }

    public void setOutboundTime(String outboundTime) {
        this.outboundTime = outboundTime;
    }

    public void setInboundDate(String inboundDate) {
        this.inboundDate = inboundDate;
    }

    public void setInboundTime(String inboundTime) {
        this.inboundTime = inboundTime;
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

    @Override
    public String toString() {
        return "FlightPackage{" +
                "uri='" + uri + '\'' +
                ", linkBody='" + linkBody + '\'' +
                ", price=" + price +
                ", outboundId='" + outboundId + '\'' +
                ", inboundId='" + inboundId + '\'' +
                ", deeplink='" + deeplink + '\'' +
                ", outboundDate='" + outboundDate + '\'' +
                ", outboundTime='" + outboundTime + '\'' +
                ", inboundDate='" + inboundDate + '\'' +
                ", inboundTime='" + inboundTime + '\'' +
                ", outboundCarrier='" + outboundCarrier + '\'' +
                ", outboundCity='" + outboundCity + '\'' +
                ", outboundAirportCode='" + outboundAirportCode + '\'' +
                ", outboundSegments=" + outboundSegments +
                ", inboundCarrier='" + inboundCarrier + '\'' +
                ", inboundCity='" + inboundCity + '\'' +
                ", inboundAirportCode='" + inboundAirportCode + '\'' +
                ", inboundSegments=" + inboundSegments +
                '}';
    }

    // Constructor Expanded
    // toBundle
}
