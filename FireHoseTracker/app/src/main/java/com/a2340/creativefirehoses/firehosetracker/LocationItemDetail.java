package com.a2340.creativefirehoses.firehosetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocationItemDetail extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationdetail);

        Intent intent = getIntent();
        final int locationPosition = intent.getIntExtra("locationPosition", 0);
        final String currentUser = intent.getStringExtra("currentUser");

        LocationModel model = LocationModel.INSTANCE;
        LocationItem currentLocation = model.getLocations().get(locationPosition);

        TextView locationName = (TextView) findViewById(R.id.location_name);
        locationName.setText("Location Name: " + currentLocation.getLocationName());

        TextView latitude = (TextView) findViewById(R.id.latitude);
        latitude.setText("Latitude: " + currentLocation.getLatitude());

        TextView longitude = (TextView) findViewById(R.id.longitude);
        longitude.setText("Longitude: " + currentLocation.getLongitude());

        TextView streetAddress = (TextView) findViewById(R.id.street_address);
        streetAddress.setText("Street Address: " + currentLocation.getStreetAddress());

        TextView city = (TextView) findViewById(R.id.city);
        city.setText("City: " + currentLocation.getCity());

        TextView state = (TextView) findViewById(R.id.state);
        state.setText("State: " + currentLocation.getState());

        TextView zip = (TextView) findViewById(R.id.zip);
        zip.setText("Zip: " + currentLocation.getZip());

        TextView type = (TextView) findViewById(R.id.type);
        type.setText("Type: " + currentLocation.getType());

        TextView phoneNum = (TextView) findViewById(R.id.phone_num);
        phoneNum.setText("Phone Number: " + currentLocation.getPhoneNum());

        TextView website = (TextView) findViewById(R.id.website);
        website.setText("Website: " + currentLocation.getWebsite());

        Button viewDonations = (Button) findViewById(R.id.view_donations);
        viewDonations.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (LocationItemDetail.this, ViewDonationsActivity.class);
                intent.putExtra("locationPosition", locationPosition);
                intent.putExtra("currentUser", currentUser);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(this, LocationItemDetail.class);
        startActivity(intent);
    }
}
