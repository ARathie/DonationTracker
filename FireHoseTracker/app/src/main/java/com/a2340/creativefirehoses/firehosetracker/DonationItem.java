package com.a2340.creativefirehoses.firehosetracker;

public class DonationItem {

    private String donationName;
    private String timeStamp;
    private String location;
    private String shortDescrip;
    private String fullDescrip;
    private String value;
    private String category;

    public DonationItem(String donationName, String timeStamp, String location, String shortDescrip,
                        String fullDescrip, String value, String category) {
        this.donationName = donationName;
        this.timeStamp = timeStamp;
        this.location = location;
        this.shortDescrip = shortDescrip;
        this.fullDescrip = fullDescrip;
        this.value = value;
        this.category = category;
    }


    public String getDonationName() { return donationName; }
    public String getTimeStamp() { return timeStamp; }
    public String getLocation() { return location; }
    public String getShortDescrip() { return shortDescrip; }
    public String getFullDescrip() { return fullDescrip;}
    public String getValue() { return value;}
    public String getCategory() { return category;}
}
