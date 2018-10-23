package com.a2340.creativefirehoses.firehosetracker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class AddDonationActivity extends AppCompatActivity {

    private int locationPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        Intent intent = getIntent();
        locationPosition = intent.getIntExtra("locationPosition", 0);

        LocationModel model = LocationModel.INSTANCE;
        final LocationItem currentLocation = model.getLocations().get(locationPosition);

        Button buttonCancel = (Button) findViewById(R.id.cancel_button);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (AddDonationActivity.this, ViewDonationsActivity.class);
                intent.putExtra("locationPosition", locationPosition);
                startActivity(intent);
            }
        });

        final EditText donationName = (EditText) findViewById(R.id.donation_name);
        final EditText shortDescrip = (EditText) findViewById(R.id.short_descrip);


        Button addDonation = (Button) findViewById(R.id.add_donation);
        addDonation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String dName = donationName.getText().toString();
                String shortDes = shortDescrip.getText().toString();

                if (dName.length() == 0) {
                    CharSequence error_donation_name_blank = "Please enter the donation name.";
                    donationName.setError(error_donation_name_blank);
                    donationName.requestFocus();
                }
                else if (shortDes.length() == 0) {
                    CharSequence error_short_descrip_blank = "Please enter a short description.";
                    shortDescrip.setError(error_short_descrip_blank);
                    shortDescrip.requestFocus();
                }
                else {
                    currentLocation.addDonation(new DonationItem(dName, "timestamp", "location", shortDes,
                            "fullDescrip", "value", "category"));
                    Intent intent = new Intent (AddDonationActivity.this, ViewDonationsActivity.class);
                    intent.putExtra("locationPosition", locationPosition);
                    startActivity(intent);
                }
            }
        });

    }
}

