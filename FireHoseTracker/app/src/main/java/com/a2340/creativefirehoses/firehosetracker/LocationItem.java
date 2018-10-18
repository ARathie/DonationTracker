package com.a2340.creativefirehoses.firehosetracker;

public class LocationItem {
    private String locationName;
    private double latitude;
    private double longitude;
    private String streetAddress;
    private String city;
    private String state;
    private int zip;
    private String type;
    private String phoneNum;
    private String website;

    public LocationItem(String locationName, double latitude, double longitude,
                        String streetAddress, String city, String state,
                        int zip, String type, String phoneNum, String website) {
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phoneNum = phoneNum;
        this.website = website;
    }


    public String toString() {
        return locationName;
    }

    public String getLocationName() { return locationName; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getStreetAddress() { return streetAddress;}
    public String getCity() { return city;}
    public String getState() { return state;}
    public int getZip() { return zip;}
    public String getType() { return type;}
    public String getPhoneNum() { return phoneNum;}
    public String getWebsite() { return website;}
}
