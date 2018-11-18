package com.a2340.creativefirehoses.firehosetracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.a2340.creativefirehoses.firehosetracker.controllers.RegistrationActivity;
import com.a2340.creativefirehoses.firehosetracker.controllers.WelcomeActivity;
import com.a2340.creativefirehoses.firehosetracker.model.UserList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.a2340.creativefirehoses.firehosetracker", appContext.getPackageName());
    }

    @Test
    public void useValidateUserAndPass1() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        EditText editUsername = new EditText(appContext);
        EditText editPassword = new EditText(appContext);

        editUsername.setText("akristanto6");
        editPassword.setText("akristanto6");

        assertEquals(true, RegistrationActivity.validateUserAndPass(editUsername, editPassword));
    }

    @Test
    public void useValidateUserAndPass2() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        EditText editUsername = new EditText(appContext);
        EditText editPassword = new EditText(appContext);

        editUsername.setText("");
        editPassword.setText("akristanto6");

        assertEquals(false, RegistrationActivity.validateUserAndPass(editUsername, editPassword));
    }

    @Test
    public void useValidateUserAndPass3() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        EditText editUsername = new EditText(appContext);
        EditText editPassword = new EditText(appContext);

        editUsername.setText("akristanto6");
        editPassword.setText("");

        assertEquals(false, RegistrationActivity.validateUserAndPass(editUsername, editPassword));
    }

}
