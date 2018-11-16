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
        if (donationName == "") {
            throw new IllegalArgumentException("donationName is empty.");
        }
        if (timeStamp == "") {
            throw new IllegalArgumentException("timeStamp is empty.");
        }
        if (location == "") {
            throw new IllegalArgumentException("location is empty.");
        }
        if (shortDescription == "" || fullDescription == "") {
            throw new IllegalArgumentException("One of descriptions is empty.");
        }
        if (value == "") {
            throw new IllegalArgumentException("donationName is empty.");
        }
        if (category == "") {
            throw new IllegalArgumentException("category is empty.");
        }
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
