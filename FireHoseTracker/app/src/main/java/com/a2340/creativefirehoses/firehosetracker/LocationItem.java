package com.a2340.creativefirehoses.firehosetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//        SharedPreferences sharedPref = getSharedPreferences("donationList", Context.MODE_PRIVATE);
//        Map<String, ?> storedDonations = sharedPref.getAll();
//        // StringSet that is the values in the Map. Index 0: strDate, 1: currentLocation.getLocationName(), 2: shortDes, 3: fullDes, 4: val, 5: ctgry)
//        for (String name : storedDonations.keySet()) {
//            donationNames.add(name + " - " + storedDonations.get(name).toArray());
//            donationList.add

    }


    public String toString() {
        return locationName;
    }
    public void addDonation(DonationItem donation) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> newDonation = new HashMap<>();
        newDonation.put("name", donation.getDonationName());
        newDonation.put("date", donation.getTimeStamp());
        newDonation.put("location", donation.getLocation());
        newDonation.put("short description", donation.getShortDescrip());
        newDonation.put("full description", donation.getFullDescrip());
        newDonation.put("value", donation.getValue());
        newDonation.put("category", donation.getCategory());
        db.collection("donations")
                .add(donation)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Donation", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Donation", "Error adding document", e);
                    }
                });
        donationList.add(donation);
        donationNames.add(donation.getDonationName() + " - " + donation.getShortDescrip());
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
