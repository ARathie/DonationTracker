package com.a2340.creativefirehoses.firehosetracker.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class LocationModel {
    public static final LocationModel INSTANCE = new LocationModel();

    private final List<LocationItem> locations;
    private static List<String> locationNames;

    private LocationModel() {
        locations = new ArrayList<>();
        locationNames = new ArrayList<>();

    }

    /**
     * Add a location to the list of locations
     * @param item item
     */
    public void addItem(LocationItem item) {
        if (item == null) {
            return;
        }
        locations.add(item);
        locationNames.add(item.getLocationName());
    }

    /**
     *
     * @return all LocationItems, each of which contains the details of a location
     */
    public List<LocationItem> getLocations() {
        return locations;
    }

    /**
     * @return the names of all locations
     */
    public static List<String> getLocationNames() {
        return locationNames;
    }

    /**
     *
     * @param name name
     * @return the LocationItem with the particular name if it exists, otherwise null
     */
    public LocationItem findItemByName(String name) {
        for (LocationItem d : locations) {
            if (d.getLocationName().equals(name)) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + name);
        return null;
    }
}

