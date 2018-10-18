package com.a2340.creativefirehoses.firehosetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class MainActivity  extends AppCompatActivity {

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

    /**
     * Button handler for the load button
     *
     * @param view  the actual button object that was pressed
     */
    public void onLoadButtonPressed(View view) {
        readSDFile();
        Intent intent = new Intent(this, LocationItemListActivity.class);
        startActivity(intent);
    }

    public static final int LOCATION_NAME = 0;
    public static final int LATITUDE = 1;
    public static final int LONGITUDE = 2;
    public static final int STREET_ADDRESS = 3;
    public static final int CITY = 4;
    public static final int STATE = 5;
    public static final int ZIP = 6;
    public static final int TYPE = 7;
    public static final int PHONE_NUM = 8;
    public static final int WEBSITE = 9;
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
                model.addItem(new LocationItem(tokens[LOCATION_NAME], Double.valueOf(tokens[LATITUDE]),
                        Double.valueOf(tokens[LONGITUDE]), tokens[STREET_ADDRESS], tokens[CITY], tokens[STATE],
                        Integer.valueOf(tokens[ZIP]), tokens[TYPE], tokens[PHONE_NUM], tokens[WEBSITE]));
            }
            br.close();
        } catch (IOException e) {

        }

    }


}
