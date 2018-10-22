package com.a2340.creativefirehoses.firehosetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DonationDetail extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationdetail);

        Intent intent = getIntent();
        int donationPosition = intent.getIntExtra("donationPosition", 0);
        int locationPosition = intent.getIntExtra("locationPosition", 0);

        LocationModel model = LocationModel.INSTANCE;
        LocationItem currentLocation = model.getLocations().get(locationPosition);
        DonationItem currentDonation = currentLocation.getDonationList().get(donationPosition);

        TextView donationName = (TextView) findViewById(R.id.donation_name);
        donationName.setText("Donation Name: " + currentDonation.getDonationName());

        TextView timeStamp = (TextView) findViewById(R.id.time_stamp);
        timeStamp.setText("Time Stamp: " + currentDonation.getTimeStamp());

        TextView location = (TextView) findViewById(R.id.location);
        location.setText("Longitude: " + currentDonation.getLocation());

        TextView fullDescription = (TextView) findViewById(R.id.full_descrip);
        fullDescription.setText("Full Description: " + currentDonation.getFullDescrip());

        TextView value = (TextView) findViewById(R.id.value);
        value.setText("City: " + currentDonation.getValue());

        TextView category = (TextView) findViewById(R.id.category);
        category.setText("State: " + currentDonation.getCategory());

    }
}