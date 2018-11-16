package com.a2340.creativefirehoses.firehosetracker.model;

import android.database.Cursor;

import com.a2340.creativefirehoses.firehosetracker.controllers.WelcomeActivity;
import com.a2340.creativefirehoses.firehosetracker.model.DonationItem;

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
        if (WelcomeActivity.itemsDB != null) {
            Cursor cursor = WelcomeActivity.itemsDB.getItemsFromLocation(this.locationName);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                donationNames.add(cursor.getString(cursor.getColumnIndex("itemName")) + " - " + cursor.getString(cursor.getColumnIndex("shortDescription")));
                addToDonationList(new DonationItem(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
                cursor.moveToNext();
            }
        }
    }


    public String toString() {
        return locationName;
    }
    public void addDonation(DonationItem donation) {

        WelcomeActivity.itemsDB.saveItem(donation.getDonationName(), donation.getTimeStamp(), donation.getLocation(), donation.getShortDescrip(), donation.getFullDescrip(), donation.getValue(), donation.getCategory());
        donationList.add(donation);
        donationNames.add(donation.getDonationName() + " - " + donation.getShortDescrip());
    }

    public void addToDonationList(DonationItem donation) {
        donationList.add(donation);

    }
    public void removeDonation(DonationItem donation) {
        donationList.remove(donation);
        donationNames.remove(donation.getDonationName() + " - " + donation.getShortDescrip());
    }
    public List<DonationItem> getDonationList() { return donationList; }
    public List<String> getDonationNames() { return donationNames; }

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
