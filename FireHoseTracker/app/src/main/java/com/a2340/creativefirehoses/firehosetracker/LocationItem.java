package com.a2340.creativefirehoses.firehosetracker;

import java.util.ArrayList;
import java.util.List;

public class LocationItem {
    private String locationName;
    private String latitude;
    private String longitude;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phoneNum;
    private String website;
    private List<DonationItem> donationList;
    private List<String> donationNames;

    public LocationItem(String locationName, String latitude, String longitude,
                        String streetAddress, String city, String state,
                        String zip, String type, String phoneNum, String website) {
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
        donationList = new ArrayList<>();
        donationNames = new ArrayList<>();
    }


    public String toString() {
        return locationName;
    }
    public void addDonation(DonationItem donation) {
        donationList.add(donation);
        donationNames.add(donation.getDonationName());
    }
    public void removeDonation(DonationItem donation) {
        donationList.remove(donation);
        donationNames.remove(donation.getDonationName());
    }
    public List<DonationItem> getDonationList(){ return donationList; }
    public List<String> getDonationNames(){ return donationNames; }

    public String getLocationName() { return locationName; }
    public String getLatitude() { return latitude; }
    public String getLongitude() { return longitude; }
    public String getStreetAddress() { return streetAddress;}
    public String getCity() { return city;}
    public String getState() { return state;}
    public String getZip() { return zip;}
    public String getType() { return type;}
    public String getPhoneNum() { return phoneNum;}
    public String getWebsite() { return website;}
}
