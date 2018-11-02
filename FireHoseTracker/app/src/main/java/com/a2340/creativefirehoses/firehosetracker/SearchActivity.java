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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class SearchActivity extends AppCompatActivity {


    private EditText itemField;
    private Spinner category;
    private EditText locField;
    private Model model = Model.getInstance();
    private ArrayList<Item> inv;
    private Location loc;
    private ArrayList<String> cats;

    private int locationPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);

        itemField = findViewById(R.id.editText2);
        category = findViewById(R.id.LocSpinner);
        locField = findViewById(R.id.editText);

        cats = new ArrayList<>(Arrays.asList(Item.categories));
        cats.add(0, "");
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, cats);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        if (getIntent().hasExtra("Act") && getIntent().getStringExtra("Act").equals("LocationRecyclerActivity")) {
            loc = getIntent().getParcelableExtra("Location");
            itemField.setText("");
            locField.setText(loc.getName());
        } else {
            itemField.setText("");
            locField.setText("");
            category.setSelection(0);
        }

    }

    public void onBackPressed(View v) {
        Intent intent = new Intent(this, AppLandingActivity.class);
        startActivity(intent);
    }

    public void onSearchPressed(View v) {
        Intent intent = new Intent(this, InventoryRecyclerActivity.class);
        inv = model.itemSearch(itemField.getText().toString(), category.getSelectedItem().toString(), loc);
        if (inv.size() == 0) {
            Snackbar failed = Snackbar.make(v, "No matches!", Snackbar.LENGTH_SHORT);
            failed.show();
        } else {
            intent.putParcelableArrayListExtra("Inventory", inv);
            intent.putExtra("Act", "SearchActivity");
            startActivity(intent);
        }
    }

    public void onSearchLocPressed(View v) {
        Intent intent = new Intent(this, LocationRecyclerActivity.class);
        ArrayList<Location> locs = model.locSearch(locField.getText().toString());
        if (locs.size() == 0) {
            Snackbar failed = Snackbar.make(v, "No matches!", Snackbar.LENGTH_SHORT);
            failed.show();
        } else {
            intent.putParcelableArrayListExtra("Locations",  locs);
            intent.putExtra("Act", "SearchActivity");
            startActivity(intent);
        }
    }

}

