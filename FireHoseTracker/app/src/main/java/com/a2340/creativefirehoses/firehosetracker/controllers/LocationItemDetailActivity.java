package com.a2340.creativefirehoses.firehosetracker.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a2340.creativefirehoses.firehosetracker.model.LocationItem;
import com.a2340.creativefirehoses.firehosetracker.model.LocationModel;
import com.a2340.creativefirehoses.firehosetracker.R;

@SuppressWarnings("RedundantCast")
public class LocationItemDetailActivity extends Activity {

    /**
     * Sets the layout for the location item details
     * @param savedInstanceState instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationdetail);

        Intent intent = getIntent();
        final int locationPosition = intent.getIntExtra("locationPosition", 0);

        LocationModel model = LocationModel.INSTANCE;
        LocationItem currentLocation = model.getLocations().get(locationPosition);

        TextView locationName = (TextView) findViewById(R.id.location_name);
        String name = "Donation Name: " + currentLocation.getLocationName();
        locationName.setText(name);

        TextView latitude = (TextView) findViewById(R.id.latitude);
        String latitide_text = "Latitude: " + currentLocation.getLatitude();
        latitude.setText(latitide_text);

        TextView longitude = (TextView) findViewById(R.id.longitude);
        String longitude_text = "Longitude: " + currentLocation.getLongitude();
        longitude.setText(longitude_text);

        TextView streetAddress = (TextView) findViewById(R.id.street_address);
        String address = "Street Address: " + currentLocation.getStreetAddress();
        streetAddress.setText(address);

        TextView city = (TextView) findViewById(R.id.city);
        String city_text = "City: " + currentLocation.getCity();
        city.setText(city_text);

        TextView state = (TextView) findViewById(R.id.state);
        String state_text = "State: " + currentLocation.getState();
        state.setText(state_text);

        TextView zip = (TextView) findViewById(R.id.zip);
        String zip_text = "Zip: " + currentLocation.getZip();
        zip.setText(zip_text);

        TextView type = (TextView) findViewById(R.id.type);
        String type_text = "Type: " + currentLocation.getType();
        type.setText(type_text);

        TextView phoneNum = (TextView) findViewById(R.id.phone_num);
        String phone = "Phone Number: " + currentLocation.getPhoneNum();
        phoneNum.setText(phone);

        TextView website = (TextView) findViewById(R.id.website);
        String web = "Website: " + currentLocation.getWebsite();
        website.setText(web);

        Button viewDonations = (Button) findViewById(R.id.view_donations);
        viewDonations.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (LocationItemDetailActivity.this, ViewDonationsActivity.class);
                intent.putExtra("locationPosition", locationPosition);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(this, ViewLocationsActivity.class);
        startActivity(intent);
    }
}
