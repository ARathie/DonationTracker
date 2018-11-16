package com.a2340.creativefirehoses.firehosetracker.model;

import android.database.Cursor;

import com.a2340.creativefirehoses.firehosetracker.controllers.WelcomeActivity;
import com.a2340.creativefirehoses.firehosetracker.model.DonationItem;

import java.util.ArrayList;
import java.util.List;

public class LocationItem {
    private final String locationName;
    private final String latitude;
    private final String longitude;
    private final String streetAddress;
    private final String city;
    private final String state;
    private final String zip;
    private final String type;
    private final String phoneNum;
    private final String website;
    private final List<DonationItem> donationList;
    private final List<String> donationNames;


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

    /**
     * Add donation to the database
     * @param donation donation
     */
    public void addDonation(DonationItem donation) {

        WelcomeActivity.itemsDB.saveItem(donation.getDonationName(), donation.getTimeStamp(), donation.getLocation(), donation.getShortDescrip(), donation.getFullDescrip(), donation.getValue(), donation.getCategory());
        donationList.add(donation);
        donationNames.add(donation.getDonationName() + " - " + donation.getShortDescrip());
    }

    /**
     * Add donation to the temporary donationList (not the database)
     * @param donation donation
     */
    public void addToDonationList(DonationItem donation) {
        donationList.add(donation);

    }

    /**
     * Remove donation from the database and list
     * @param donation donation
     */
    public void removeDonation(DonationItem donation) {
        donationList.remove(donation);
        donationNames.remove(donation.getDonationName() + " - " + donation.getShortDescrip());
    }

    /**
     *
     * @return the list of all donations
     */
    public List<DonationItem> getDonationList() { return donationList; }

    /**
     *
     * @return the names of all donations
     */
    public List<String> getDonationNames() { return donationNames; }

    /**
     *
     * @return the name of the location
     */
    public String getLocationName() { return locationName; }

    /**
     *
     * @return the latitude of the location
     */
    public String getLatitude() { return latitude; }

    /**
     *
     * @return the longitude of the location
     */
    public String getLongitude() { return longitude; }

    /**
     *
     * @return the street address of the location
     */
    public String getStreetAddress() { return streetAddress;}

    /**
     *
     * @return the city of the location
     */
    public String getCity() { return city;}

    /**
     *
     * @return the state of the location
     */
    public String getState() { return state;}

    /**
     *
     * @return the zip of the location
     */
    public String getZip() { return zip;}

    /**
     *
     * @return the type of store
     */
    public String getType() { return type;}

    /**
     *
     * @return the phone number of the store
     */
    public String getPhoneNum() { return phoneNum;}

    /**
     *
     * @return the website of the store
     */
    public String getWebsite() { return website;}
}
