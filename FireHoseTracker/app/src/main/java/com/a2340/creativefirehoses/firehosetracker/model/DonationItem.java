package com.a2340.creativefirehoses.firehosetracker.model;

public class DonationItem {

    private String donationName;
    private String timeStamp;
    private String location;
    private String shortDescription;
    private String fullDescription;
    private String value;
    private String category;

    public DonationItem(String donationName, String timeStamp, String location, String shortDescription,
                        String fullDescription, String value, String category) {
        this.donationName = donationName;
        this.timeStamp = timeStamp;
        this.location = location;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.value = value;
        this.category = category;
    }


    public String getDonationName() { return donationName; }
    public String getTimeStamp() { return timeStamp; }
    public String getLocation() { return location; }
    public String getShortDescrip() { return shortDescription; }
    public String getFullDescrip() { return fullDescription;}
    public String getValue() { return value;}
    public String getCategory() { return category;}
}
