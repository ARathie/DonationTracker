package com.a2340.creativefirehoses.firehosetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.a2340.creativefirehoses.firehosetracker.UserList;

import java.util.Arrays;
import java.util.List;


public class AddEmployeeLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee_location);

        final EditText employeeLocation = (EditText) findViewById(R.id.employee_location);

        Button buttonSubmit = (Button) findViewById(R.id.submit_button);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String eLocation = employeeLocation.getText().toString();

                if (eLocation.length() == 0) {
                    CharSequence error_location_blank = "Please enter a location.";
                    employeeLocation.setError(error_location_blank);
                    employeeLocation.requestFocus();
                } else {
                    UserList.addLocation(UserList.getCurrentUser(), eLocation);
                    Intent intent = new Intent(AddEmployeeLocation.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
