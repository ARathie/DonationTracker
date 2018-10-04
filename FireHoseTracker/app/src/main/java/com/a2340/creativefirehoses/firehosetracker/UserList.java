package com.a2340.creativefirehoses.firehosetracker;
import java.util.Map;
import java.util.HashMap;

import java.util.ArrayList;

public class UserList {
    private static Map<String, String> users = new HashMap<>();

    public static boolean containsUser(String username) {
        return users.containsKey(username);
    }

    public static String getPassword(String username) {
        return users.get(username);
    }

    public static void addUser(String username, String password) {
        users.put(username, password);
    }

}
