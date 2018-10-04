package com.a2340.creativefirehoses.firehosetracker;
import java.util.Map;
import java.util.HashMap;

public class UserList {
    private static Map<String, String> users;

    UserList() {
        if (users == null) {
            users = new HashMap();
        }
    }

    public static String getPassword(String username) {
        return users.get(username);
    }

    public static void setPassword(String username, String password) {
        users.put(username, password);
    }

}
