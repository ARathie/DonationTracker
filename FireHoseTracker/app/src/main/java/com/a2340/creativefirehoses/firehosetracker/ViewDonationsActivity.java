package com.a2340.creativefirehoses.firehosetracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewDonationsActivity extends ListActivity implements AdapterView.OnItemClickListener{

    private int locationPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        Intent intent = getIntent();
        locationPosition = intent.getIntExtra("position", 0);

        LocationModel model = LocationModel.INSTANCE;
        LocationItem currentLocation = model.getLocations().get(locationPosition);

        ListView list = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, currentLocation.getDonationNames());
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
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
}
