package com.a2340.creativefirehoses.firehosetracker.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.a2340.creativefirehoses.firehosetracker.model.DonationItem;
import com.a2340.creativefirehoses.firehosetracker.model.LocationItem;
import com.a2340.creativefirehoses.firehosetracker.model.LocationModel;
import com.a2340.creativefirehoses.firehosetracker.R;

@SuppressWarnings("RedundantCast")
public class DonationDetailActivity extends Activity {

    /**
     * Sets the layout for the donation details
     * @param savedInstanceState a parameter for this activty
     */
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
        String name = "Donation Name: " + currentDonation.getDonationName();
        donationName.setText(name);

        TextView timeStamp = (TextView) findViewById(R.id.time_stamp);
        String stamp = "Time Stamp: " + currentDonation.getTimeStamp();
        timeStamp.setText(stamp);

        TextView location = (TextView) findViewById(R.id.location);
        String location_text = "Location: " + currentDonation.getLocation();
        location.setText(location_text);

        TextView fullDescription = (TextView) findViewById(R.id.full_descrip);
        String description = "Full Description: " + currentDonation.getFullDescrip();
        fullDescription.setText(description);

        TextView value = (TextView) findViewById(R.id.value);
        String value_text = "Value: " + currentDonation.getValue();
        value.setText(value_text);

        TextView category = (TextView) findViewById(R.id.category);
        String category_text = "Category: " + currentDonation.getCategory();
        category.setText(category_text);

    }
}
