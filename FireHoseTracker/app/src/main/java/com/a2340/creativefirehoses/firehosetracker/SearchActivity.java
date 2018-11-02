package com.a2340.creativefirehoses.firehosetracker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private EditText searchEntry;
    private RadioButton categoryButton;
    private RadioButton itemButton;
    private RadioGroup radioGroup;
    private Spinner locationChoice;
    private ListView resultsList;
    private Button searchButton;

//    private Model model = Model.getInstance();
//    private ArrayList<Item> inv;
//    private Location loc;
    private ArrayList<String> cats;

    private int locationPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEntry = findViewById(R.id.searchEntry);
        categoryButton = findViewById(R.id.categoryRadio);
        itemButton = findViewById(R.id.itemRadio);
        radioGroup = findViewById(R.id.radioGroup);
        searchButton = findViewById(R.id.searchButton);

        locationChoice = (Spinner) findViewById(R.id.locationSpinner);
        List<String> locations = new ArrayList<>(LocationModel.getLocationNames());
        locations.add(0, "All");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationChoice.setAdapter(adapter);

        resultsList = (ListView) findViewById(R.id.resultsList);


        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton selectedButton = (RadioButton) findViewById(selectedId);
                String itemOrCategory = selectedButton.getText().toString();
                if (!(itemOrCategory.equals("Item") || itemOrCategory.equals("Category"))){
                    Context context = getApplicationContext();
                    CharSequence text = "You must select Item or Category!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    onSearchPressed(v);
                }

            }
        });

//
//        cats = new ArrayList<>(Arrays.asList(Item.categories));
//        cats.add(0, "");
//        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, cats);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        categoryButton.setAdapter(adapter);
//
//        if (getIntent().hasExtra("Act") && getIntent().getStringExtra("Act").equals("LocationRecyclerActivity")) {
//            loc = getIntent().getParcelableExtra("Location");
//            searchEntry.setText("");
//            locField.setText(loc.getName());
//        } else {
//            searchEntry.setText("");
//            locField.setText("");
//            categoryButton.setSelection(0);
//        }

    }

    public void onItemClick(AdapterView<?> l, View v, int locationPosition, long id) {
        Log.i("LocationListView", "You clicked Item: " + id + " at position:" + locationPosition);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, LocationItemDetail.class);
        intent.putExtra("locationPosition", locationPosition);
        startActivity(intent);
    }

    public void onBackPressed(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSearchPressed(View v) {

        String searchString = searchEntry.getText().toString();
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton selectedButton = (RadioButton) findViewById(selectedId);
        String itemOrCategory = selectedButton.getText().toString();
        if (!(itemOrCategory.equals("Item") || itemOrCategory.equals("Category"))){
            Context context = getApplicationContext();
            CharSequence text = "You must select Item or Category!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            String location = locationChoice.getSelectedItem().toString();

            List<String> results = new ArrayList<>();

            if (itemOrCategory.equals("Category")){

                Cursor cursor = WelcomeActivity.itemsDB.getItemsFromCategory(searchString, location);

                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    results.add(cursor.getString(cursor.getColumnIndex("itemName")));
                    cursor.moveToNext();
                }


            } else if(itemOrCategory.equals("Item")) {
                Cursor cursor = WelcomeActivity.itemsDB.getItemsFromName(searchString, location);

                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    results.add(cursor.getString(cursor.getColumnIndex("itemName")));
                    cursor.moveToNext();
                }
            }


            if (results.size() == 0) {
                Context context = getApplicationContext();
                CharSequence text = "There were no matches";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, results);
            resultsList.setAdapter(adapter2);
            resultsList.setOnItemClickListener(this);
        }

    }

    public void onSearchLocPressed(View v) {
//        Intent intent = new Intent(this, LocationRecyclerActivity.class);
//        ArrayList<Location> locs = model.locSearch(locField.getText().toString());
//        if (locs.size() == 0) {
//            Snackbar failed = Snackbar.make(v, "No matches!", Snackbar.LENGTH_SHORT);
//            failed.show();
//        } else {
//            intent.putParcelableArrayListExtra("Locations",  locs);
//            intent.putExtra("Act", "SearchActivity");
//            startActivity(intent);
//        }
    }

}

