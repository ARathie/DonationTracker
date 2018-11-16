package com.a2340.creativefirehoses.firehosetracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.database.Cursor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.a2340.creativefirehoses.firehosetracker.model.LocationModel;
import com.a2340.creativefirehoses.firehosetracker.R;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Extracts data from the corresponding location that the search processes
     * @param l
     * @param v
     * @param locationPosition
     * @param id
     */
    public void onItemClick(AdapterView<?> l, View v, int locationPosition, long id) {
        Log.i("LocationListView", "You clicked Item: " + id + " at position:" + locationPosition);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, LocationItemDetailActivity.class);
        intent.putExtra("locationPosition", locationPosition);
        startActivity(intent);
    }

    /**
     * Goes to MainActivity when the back button is pressed
     * @param v
     */
    public void onBackPressed(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Logic for processing the text in the search
     * @param v
     */
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
            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, results);
            resultsList.setAdapter(adapter2);
            resultsList.setOnItemClickListener(this);
        }

    }

    /**
     * does nothing when searchLocPressed
     * @param v
     */
    public void onSearchLocPressed(View v) {

    }

}

