package com.a2340.creativefirehoses.firehosetracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.a2340.creativefirehoses.firehosetracker.model.LocationModel;
import com.a2340.creativefirehoses.firehosetracker.R;
import com.a2340.creativefirehoses.firehosetracker.model.UserList;


public class AddEmployeeLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee_location);

        final Spinner employeeLocation = (Spinner) findViewById(R.id.employee_location);
        LocationModel model = LocationModel.INSTANCE;

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, model.getLocationNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employeeLocation.setAdapter(adapter);

        Button buttonSubmit = (Button) findViewById(R.id.submit_button);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String eLocation = employeeLocation.getSelectedItem().toString();

                UserList.addLocation(UserList.getCurrentUser(), eLocation);
                Intent intent = new Intent(AddEmployeeLocationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
