package com.a2340.creativefirehoses.firehosetracker.model;
import java.util.Map;
import java.util.HashMap;


import com.a2340.creativefirehoses.firehosetracker.controllers.WelcomeActivity;
import com.a2340.creativefirehoses.firehosetracker.model.SQliteHelperUsers;

public class UserList {

    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, String> userTypes = new HashMap<>();
    private static final Map<String, String> employeeLocation = new HashMap<>();
    public static String currentUser;
    private static final SQliteHelperUsers usersDB = WelcomeActivity.usersDB;

    /**
     * @param username username
     * @return true if the user exists, else false
     */
    public static boolean containsUser(String username) {
        return users.containsKey(username);
    }

    /**
     *
     * @param username username
     * @return the password of the corresponding username
     */
    public static String getPassword(String username) {
        return users.get(username);
    }

    /**
     *
     * @param username username
     * @return the type of the user
     */
    public static String getType(String username) {
        return userTypes.get(username);
    }


//    public static void populateUserList() {
//        Cursor cursor = WelcomeActivity.usersDB.getAll();
//        cursor.moveToFirst();
//        while(!cursor.isAfterLast()){
//            users.put(cursor.getString(cursor.getColumnIndex("username")), cursor.getString(cursor.getColumnIndex("password")));
//            userTypes.put(cursor.getString(cursor.getColumnIndex("username")), cursor.getString(2));
//            cursor.moveToNext();
//        }
//    }

//    public static void loadUsers() {
//
//        users.put(username, password);
//        userTypes.put(username, type);
//    }

    /**
     * adds a user with the corresponding username, password and type to the list
     * @param username username
     * @param password password
     * @param type type
     */
    public static void addUser(String username, String password, String type) {
        addToDatabase(username, password, type);
        currentUser = username;
        users.put(username, password);
        userTypes.put(username, type);
    }

    /**
     * adds a user with the corresponding username, password and type to the database
     * @param username username
     * @param password password
     * @param type type
     */
    private static void addToDatabase(String username, String password, String type) {

        usersDB.saveUser(username, password, type);

    }

    /**
     * Sets the location of the user
     * @param username username
     * @param location location
     */
    public static void addLocation(String username, String location) {
        employeeLocation.put(username, location);
    }

    /**
     *
     * @param username username
     * @return the location of the particular user
     */
    public static String getLocation(String username) {
        return employeeLocation.get(username);
    }

    /**
     *
     * @return the name of the user that is logged in
     */
    public static String getCurrentUser(){ return currentUser; }
}
