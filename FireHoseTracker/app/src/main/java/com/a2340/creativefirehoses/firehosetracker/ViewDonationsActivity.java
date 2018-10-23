package com.a2340.creativefirehoses.firehosetracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ViewDonationsActivity extends ListActivity implements AdapterView.OnItemClickListener{

    private int locationPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donations);

        Intent intent = getIntent();
        locationPosition = intent.getIntExtra("locationPosition", 0);
        String currentUser = intent.getStringExtra("currentUser");

        LocationModel model = LocationModel.INSTANCE;
        LocationItem currentLocation = model.getLocations().get(locationPosition);

        ListView list = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
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

//        if (!UserList.getType(currentUser).equals("Location Employee")
//                && !UserList.getType(currentUser).equals("Manager")) {
//            addDonation.setVisibility(View.GONE);
//        }

    }

    public void onItemClick(AdapterView<?> l, View v, int donationPosition, long id) {
        Log.i("LocationListView", "You clicked Item: " + id + " at position:" + donationPosition);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, DonationDetail.class);
        intent.putExtra("donationPosition", donationPosition);
        intent.putExtra("locationPosition", locationPosition);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(this, LocationItemDetail.class);
        intent.putExtra("locationPosition", locationPosition);
        startActivity(intent);
    }
}
