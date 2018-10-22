package com.a2340.creativefirehoses.firehosetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LocationItemDetail extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationitem);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        LocationModel model = LocationModel.INSTANCE;
        LocationItem current = model.getLocations().get(position);

        TextView locationName = (TextView) findViewById(R.id.location_name);
        locationName.setText("Location Name: " + current.getLocationName());

        TextView latitude = (TextView) findViewById(R.id.latitude);
        latitude.setText("Latitude: " + current.getLatitude());

        TextView longitude = (TextView) findViewById(R.id.longitude);
        longitude.setText("Longitude: " + current.getLongitude());

        TextView streetAddress = (TextView) findViewById(R.id.street_address);
        streetAddress.setText("Street Address: " + current.getStreetAddress());

        TextView city = (TextView) findViewById(R.id.city);
        city.setText("City: " + current.getCity());

        TextView state = (TextView) findViewById(R.id.state);
        state.setText("State: " + current.getState());

        TextView zip = (TextView) findViewById(R.id.zip);
        zip.setText("Zip: " + current.getZip());

        TextView type = (TextView) findViewById(R.id.type);
        type.setText("Type: " + current.getType());

        TextView phoneNum = (TextView) findViewById(R.id.phone_num);
        phoneNum.setText("Phone Number: " + current.getPhoneNum());

        TextView website = (TextView) findViewById(R.id.website);
        website.setText("Website: " + current.getWebsite());
    }
}
