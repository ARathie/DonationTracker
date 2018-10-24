package com.a2340.creativefirehoses.firehosetracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewLocationsActivity extends ListActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        LocationModel model = LocationModel.INSTANCE;
        ListView list = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, model.getLocationNames());
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> l, View v, int locationPosition, long id) {
        Log.i("LocationListView", "You clicked Item: " + id + " at position:" + locationPosition);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, LocationItemDetail.class);
        intent.putExtra("locationPosition", locationPosition);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
}
