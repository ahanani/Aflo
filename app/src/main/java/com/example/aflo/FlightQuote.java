package com.example.aflo;

public class FlightQuote {
    String QuoteId, MinPrice, Direct, OutboundLeg, InboundLeg, QuoteDateTime, Carrier;

    public String getCarrier() {
        return Carrier;
    }

    public void setCarrier(String carrier) {
        Carrier = carrier;
    }

    public String getQuoteId() {
        return QuoteId;
    }

    public void setQuoteId(String quoteId) {
        QuoteId = quoteId;
    }

    public String getMinPrice() {
        return MinPrice;
    }

    public void setMinPrice(String minPrice) {
        MinPrice = minPrice;
    }

    public String getDirect() {
        return Direct;
    }

    public void setDirect(String direct) {
        Direct = direct;
    }

    public String getOutboundLeg() {
        return OutboundLeg;
    }

    public void setOutboundLeg(String outboundLeg) {
        OutboundLeg = outboundLeg;
    }

    public String getInboundLeg() {
        return InboundLeg;
    }

    public void setInboundLeg(String inboundLeg) {
        InboundLeg = inboundLeg;
    }

    public String getQuoteDateTime() {
        return QuoteDateTime;
    }

    public void setQuoteDateTime(String quoteDateTime) {
        QuoteDateTime = quoteDateTime;
    }

    @Override
    public String toString() {
        return "FlightQuote{" +
                "QuoteId='" + QuoteId + '\'' +
                ", MinPrice='" + MinPrice + '\'' +
                ", Direct='" + Direct + '\'' +
                ", OutboundLeg='" + OutboundLeg + '\'' +
                ", InboundLeg='" + InboundLeg + '\'' +
                ", QuoteDateTime='" + QuoteDateTime + '\'' +
                ", Carrier='" + Carrier + '\'' +
                '}';
    }
}
