package com.a2340.creativefirehoses.firehosetracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.a2340.creativefirehoses.firehosetracker.R;
import com.a2340.creativefirehoses.firehosetracker.model.UserList;

import java.util.Arrays;
import java.util.List;


@SuppressWarnings("RedundantCast")
public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button buttonCancel = (Button) findViewById(R.id.cancel_button);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (RegistrationActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        final EditText editUsername = (EditText) findViewById(R.id.student_username_input);
        final EditText editPassword = (EditText) findViewById(R.id.student_pass_input);
        final Spinner accountType = (Spinner) findViewById(R.id.accountType);

        List<String> accounts = Arrays.asList("User", "Location Employee", "Manager", "Admin");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, accounts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountType.setAdapter(adapter);

        Button buttonRegister = (Button) findViewById(R.id.register_button);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            String type = accountType.getSelectedItem().toString();

            if (username.length() == 0) {
                CharSequence error_username_blank = "Please enter username.";
                editUsername.setError(error_username_blank);
                editUsername.requestFocus();
            }
            else if (password.length() == 0) {
                CharSequence error_password_blank = "Please enter password.";
                editPassword.setError(error_password_blank);
                editPassword.requestFocus();
            }
            else if (UserList.containsUser(username)) {
                CharSequence error_username_exists = "Username already exists.";
                editUsername.setError(error_username_exists);
                editUsername.requestFocus();
            } else if (type.equals("Location Employee")){
                UserList.addUser(username, password, type);
                Intent intent = new Intent(RegistrationActivity.this, AddEmployeeLocationActivity.class);
                startActivity(intent);
            } else {
                UserList.addUser(username, password, type);
//                SharedPreferences sharedPref = getSharedPreferences("userList", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString(username, password);
//                editor.apply();
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        });


    }
}
