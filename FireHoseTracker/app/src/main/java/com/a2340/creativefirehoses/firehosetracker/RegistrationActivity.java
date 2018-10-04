package com.a2340.creativefirehoses.firehosetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.a2340.creativefirehoses.firehosetracker.UserList;

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

        Button buttonRegister = (Button) findViewById(R.id.register_button);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            if (UserList.containsUser(username)) {
                CharSequence error_username_exists = "Username already exists.";
                editUsername.setError(error_username_exists);
                editUsername.requestFocus();
            } else {
                UserList.addUser(username, password);
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        });
    }
}
