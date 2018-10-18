package com.a2340.creativefirehoses.firehosetracker;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity {

    private Button buttonLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * Button handler for the load button
//     *
//     * @param view  the actual button object that was pressed
//     */
    public void onLoadButtonPressed(View view) {
        readSDFile();
        Intent intent = new Intent(this, LocationItemListActivity.class);
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
