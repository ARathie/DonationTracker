package com.a2340.creativefirehoses.firehosetracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ViewLocationsActivity extends ListActivity implements AdapterView.OnItemClickListener{

    private static boolean parsed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        if(!parsed) {
            readSDFile();
            parsed = true;
        }
        LocationModel model = LocationModel.INSTANCE;
        ListView list = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, model.getLocationNames());
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("LocationListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, LocationItemDetail.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public static final int LOCATION_NAME = 1;
    public static final int LATITUDE = 2;
    public static final int LONGITUDE = 3;
    public static final int STREET_ADDRESS = 4;
    public static final int CITY = 5;
    public static final int STATE = 6;
    public static final int ZIP = 7;
    public static final int TYPE = 8;
    public static final int PHONE_NUM = 9;
    public static final int WEBSITE = 10;
    /**
     * Open the sample.csv file in the /res/raw directory
     * Line Entry format:
     *   [0] - name
     *
     */
    private void readSDFile() {
        LocationModel model = LocationModel.INSTANCE;

        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                model.addItem(new LocationItem(tokens[LOCATION_NAME], tokens[LATITUDE],
                        tokens[LONGITUDE], tokens[STREET_ADDRESS], tokens[CITY], tokens[STATE],
                        tokens[ZIP], tokens[TYPE], tokens[PHONE_NUM], tokens[WEBSITE]));
            }
            br.close();
        } catch (IOException e) {

        }

    }
}
