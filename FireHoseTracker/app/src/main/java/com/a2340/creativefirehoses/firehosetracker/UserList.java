package com.a2340.creativefirehoses.firehosetracker;
import java.util.Map;
import java.util.HashMap;

import java.util.ArrayList;

public class UserList {
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, String> userTypes = new HashMap<>();

    public static boolean containsUser(String username) {
        return users.containsKey(username);
    }

    public static String getPassword(String username) {
        return users.get(username);
    }

    public static String getType(String username) { return userTypes.get(username); }

    public static void addUser(String username, String password, String type) {
        users.put(username, password);
        userTypes.put(username, type);
    }

}
