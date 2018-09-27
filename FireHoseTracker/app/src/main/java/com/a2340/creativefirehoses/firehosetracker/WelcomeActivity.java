package com.a2340.creativefirehoses.firehosetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class WelcomeActivity extends AppCompatActivity {

    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        buttonLogin = (Button) findViewById(R.id.LOGIN);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
