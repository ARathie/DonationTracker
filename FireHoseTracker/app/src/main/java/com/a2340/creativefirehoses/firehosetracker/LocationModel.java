package com.a2340.creativefirehoses.firehosetracker;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;


public class LocationModel {
    public static final LocationModel INSTANCE = new LocationModel();

    private List<LocationItem> locations;

    private LocationModel() {
        locations = new ArrayList<>();
    }

    public void addItem(LocationItem item) {
        locations.add(item);
    }

    public List<LocationItem> getLocations() {
        return locations;
    }

    public LocationItem findItemByName(String name) {
        for (LocationItem d : locations) {
            if (d.getLocationName().equals(name)) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + name);
        return null;
    }
}

