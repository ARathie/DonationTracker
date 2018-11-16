package com.a2340.creativefirehoses.firehosetracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.a2340.creativefirehoses.firehosetracker.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        Button viewAllLocations = (Button) findViewById(R.id.view_all_locations);
        viewAllLocations.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, ViewLocationsActivity.class);
                startActivity(intent);
            }
        });

        Button search = (Button) findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        Button viewMap = (Button) findViewById(R.id.view_map);
        viewMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Uses the default implementation for pressing the back button
     */
    @Override
    public void onBackPressed() {

    }

}
