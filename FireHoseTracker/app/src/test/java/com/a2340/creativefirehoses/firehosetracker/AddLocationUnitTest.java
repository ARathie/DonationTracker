package com.a2340.creativefirehoses.firehosetracker;

import org.junit.Test;

import com.a2340.creativefirehoses.firehosetracker.model.LocationModel;
import com.a2340.creativefirehoses.firehosetracker.model.LocationItem;

import static org.junit.Assert.assertEquals;

public class AddLocationUnitTest {

    @Test
    public void testAdd() {
        LocationModel model = LocationModel.INSTANCE;
        int size_0 = model.getLocations().size();
        model.addItem(new LocationItem("testName", "0", "0", "testAddress", "testCity", "testState", "00000",
                "testType", "333-333-3333", "test.com"));
        int size_1 = model.getLocations().size();
        assertEquals("Size is of the locations is incremented", 1, size_1 - size_0);
    }

    @Test
    public void testAddNull() {
        LocationModel model = LocationModel.INSTANCE;
        int size_0 = model.getLocations().size();
        model.addItem(null);
        int size_1 = model.getLocations().size();
        assertEquals("Size of locations is kept the same when a null item is added", size_0, size_1);
    }



}
