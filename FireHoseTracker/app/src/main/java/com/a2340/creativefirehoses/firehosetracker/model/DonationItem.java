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


    /**
     *
     * @return the name of the donation
     */
    public String getDonationName() { return donationName; }

    /**
     *
     * @return the timeStamp of the donation
     */
    public String getTimeStamp() { return timeStamp; }

    /**
     *
     * @return the donation's location
     */
    public String getLocation() { return location; }

    /**
     *
     * @return the short description of the donation
     */
    public String getShortDescrip() { return shortDescription; }

    /**
     *
     * @return the full description of the donation
     */
    public String getFullDescrip() { return fullDescription;}

    /**
     *
     * @return the price of the donation
     */
    public String getValue() { return value;}

    /**
     *
     * @return the category of the donation
     */
    public String getCategory() { return category;}
}
