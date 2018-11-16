package com.a2340.creativefirehoses.firehosetracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.a2340.creativefirehoses.firehosetracker.model.LocationItem;
import com.a2340.creativefirehoses.firehosetracker.model.LocationModel;
import com.a2340.creativefirehoses.firehosetracker.R;
import com.a2340.creativefirehoses.firehosetracker.model.SQliteHelperItems;
import com.a2340.creativefirehoses.firehosetracker.model.SQliteHelperUsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class WelcomeActivity extends AppCompatActivity {
    public static SQliteHelperUsers usersDB;
    public static SQliteHelperItems itemsDB;

    private static boolean parsed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersDB = new SQliteHelperUsers(this);
        itemsDB = new SQliteHelperItems(this);

//        UserList.populateUserList();

        setContentView(R.layout.activity_welcome);
        Button buttonLogin = (Button) findViewById(R.id.LOGIN);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button buttonRegister = (Button) findViewById(R.id.REGISTRATION);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (WelcomeActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        if(!parsed) {
            readSDFile();
            parsed = true;
        }
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

    /**
     * does nothing when the back button is pressed
     */
    @Override
    public void onBackPressed() {

    }
}
