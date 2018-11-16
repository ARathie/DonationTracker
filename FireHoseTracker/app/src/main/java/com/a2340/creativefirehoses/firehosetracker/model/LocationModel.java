package com.a2340.creativefirehoses.firehosetracker.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class LocationModel {
    public static final LocationModel INSTANCE = new LocationModel();

    private List<LocationItem> locations;
    private static List<String> locationNames;

    private LocationModel() {
        locations = new ArrayList<>();
        locationNames = new ArrayList<>();

    }

    public void addItem(LocationItem item) {
        if (item == null) {
            return;
        }
        locations.add(item);
        locationNames.add(item.getLocationName());
    }

    public List<LocationItem> getLocations() {
        return locations;
    }

    public static List<String> getLocationNames() {
        return locationNames;
    }

    public LocationItem findItemByName(String name) {
        for (LocationItem d : locations) {
            if (d.getLocationName().equals(name)) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + name);
        return null;
    }
}

