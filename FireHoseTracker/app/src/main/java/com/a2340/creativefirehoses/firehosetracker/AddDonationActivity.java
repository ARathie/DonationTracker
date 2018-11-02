package com.a2340.creativefirehoses.firehosetracker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
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
        final EditText fullDescrip = (EditText) findViewById(R.id.full_descrip);
        final EditText value = (EditText) findViewById(R.id.value);
        final Spinner category = (Spinner) findViewById(R.id.category);

        List<String> accounts = Arrays.asList("Clothing", "Hat", "Kitchen", "Electronics", "Household", "Other");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, accounts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);


        Button addDonation = (Button) findViewById(R.id.add_donation);
        addDonation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String dName = donationName.getText().toString();
                String shortDes = shortDescrip.getText().toString();
                String fullDes = fullDescrip.getText().toString();
                String val = "$" + value.getText().toString();
                String ctgry = category.getSelectedItem().toString();

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                String strDate = sdf.format(c.getTime());

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
                else if (fullDes.length() == 0) {
                    CharSequence error_full_descrip_blank = "Please enter a full description.";
                    fullDescrip.setError(error_full_descrip_blank);
                    fullDescrip.requestFocus();
                }
                else if (val.length() == 0) {
                    CharSequence error_val_blank = "Please enter a value.";
                    value.setError(error_val_blank);
                    value.requestFocus();
                }
                else {
                    currentLocation.addDonation(new DonationItem(dName, strDate, currentLocation.getLocationName(),
                            shortDes, fullDes, val, ctgry));
                    SharedPreferences sharedPref = getSharedPreferences("donationList", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putStringSet(dName, new HashSet<String>(Arrays.asList(strDate, currentLocation.getLocationName(), shortDes, fullDes, val, ctgry)));
                    editor.apply();
                    Intent intent = new Intent (AddDonationActivity.this, ViewDonationsActivity.class);
                    intent.putExtra("locationPosition", locationPosition);
                    startActivity(intent);
                }
            }
        });

    }
}

