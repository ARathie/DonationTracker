package com.a2340.creativefirehoses.firehosetracker.controllers;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.a2340.creativefirehoses.firehosetracker.model.LocationItem;
import com.a2340.creativefirehoses.firehosetracker.model.LocationModel;
import com.a2340.creativefirehoses.firehosetracker.R;
import com.a2340.creativefirehoses.firehosetracker.model.UserList;

@SuppressWarnings("RedundantCast")
public class ViewDonationsActivity extends ListActivity implements AdapterView.OnItemClickListener{

    private int locationPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donations);

        Intent intent = getIntent();
        locationPosition = intent.getIntExtra("locationPosition", 0);
        String currentUser = UserList.getCurrentUser();

        LocationModel model = LocationModel.INSTANCE;
        LocationItem currentLocation = model.getLocations().get(locationPosition);

        TextView noDonations = (TextView) findViewById(R.id.no_donations);
        if(currentLocation.getDonationNames().size() >= 1){
            noDonations.setVisibility(View.GONE);
        }

        ListView list = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, currentLocation.getDonationNames());
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        Button addDonation = (Button) findViewById(R.id.add_donation);
        addDonation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (ViewDonationsActivity.this, AddDonationActivity.class);
                intent.putExtra("locationPosition", locationPosition);
                startActivity(intent);
            }
        });

        addDonation.setVisibility(View.GONE);

        if (("Location Employee").equals(UserList.getType(currentUser))
                || ("Manager").equals(UserList.getType(currentUser))) {
            if (("Location Employee").equals(UserList.getType(currentUser))
                    && (currentLocation.getLocationName()).equals(UserList.getLocation(currentUser))
                    || ("Manager").equals(UserList.getType(currentUser))) {
                addDonation.setVisibility(View.VISIBLE);
            }
        }

    }

    /**
     * Processes the clicking of a donationItem so that the activity can be changed
     * and the proper text can be displayed.
     * @param l adapter
     * @param v view
     * @param donationPosition position of the donation
     * @param id id for donation
     */
    public void onItemClick(AdapterView<?> l, View v, int donationPosition, long id) {
        Log.i("LocationListView", "You clicked Item: " + id + " at position:" + donationPosition);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, DonationDetailActivity.class);
        intent.putExtra("donationPosition", donationPosition);
        intent.putExtra("locationPosition", locationPosition);
        startActivity(intent);
    }


    /**
     * Goes to LocationItemDetailActivity when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(this, LocationItemDetailActivity.class);
        intent.putExtra("locationPosition", locationPosition);
        startActivity(intent);
    }
}
