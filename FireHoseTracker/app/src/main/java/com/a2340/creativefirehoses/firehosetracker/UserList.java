package com.a2340.creativefirehoses.firehosetracker;
import java.util.Map;
import java.util.HashMap;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;

public class UserList {

    private static Map<String, String> users = new HashMap<>();
    private static Map<String, String> userTypes = new HashMap<>();
    private static Map<String, String> employeeLocation = new HashMap<>();
    private static String currentUser;
    private static SQliteHelperUsers usersDB = WelcomeActivity.usersDB;

    public static boolean containsUser(String username) {
        return users.containsKey(username);
    }

    public static String getPassword(String username) {
        return users.get(username);
    }

    public static String getType(String username) { return userTypes.get(username); }

    public static void loadUsers() {

    }

//    public static void loadUsers() {
//
//        users.put(username, password);
//        userTypes.put(username, type);
//    }

    public static void addUser(String username, String password, String type) {
        addToDatabase(username, password, type);
        currentUser = username;
        users.put(username, password);
        userTypes.put(username, type);
    }


    public static void addToDatabase(String username, String password, String type) {

        usersDB.saveUser(username, password, type);

    }

    public static void addLocation(String username, String location) {
        employeeLocation.put(username, location);
    }

    public static String getLocation(String username) {
        return employeeLocation.get(username);
    }

    public static String getCurrentUser(){ return currentUser; }
}
