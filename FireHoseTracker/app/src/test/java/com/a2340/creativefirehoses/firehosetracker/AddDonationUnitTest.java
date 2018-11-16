/*
Written by: Rohan Varshney
CS 2340: M10
 */

package com.a2340.creativefirehoses.firehosetracker;

import org.junit.Test;

import com.a2340.creativefirehoses.firehosetracker.model.DonationItem;
import com.a2340.creativefirehoses.firehosetracker.model.LocationModel;
import com.a2340.creativefirehoses.firehosetracker.model.LocationItem;

import static org.junit.Assert.assertEquals;

public class AddDonationUnitTest {

    @Test
    public void testAddTrueAndFalseValues() {
        LocationModel model = LocationModel.INSTANCE;
        int size_0 = model.getLocations().size();
        LocationItem addIn = new LocationItem("testName", "0", "0", "testAddress", "testCity", "testState", "00000",
                "testType", "333-333-3333", "test.com");
        model.addItem(addIn);
        int size_1 = model.getLocations().size();
        assertEquals("Size is of the locations is incremented", 1, size_1 - size_0);

        LocationItem testName = model.getLocations().get(0);
        assertEquals("LocationItem is retrieved", testName, addIn);

        DonationItem donation = new DonationItem("Clothes", "11/15/2018", addIn.getLocationName(),
                "Clothes for donation.", "Various apparel for children.", "200", "Clothing");

        testName.addToDonationList(donation);

        assertEquals("Size of the donationList is incremented", 1, testName.getDonationList().size());

        assertEquals("Field values are consistent", "200", testName.getDonationList().get(0).getValue());


        try {
            DonationItem donationTwo = new DonationItem("Clothes", "11/15/2018", addIn.getLocationName(),
                    "Clothes for donation.", "Various apparel for children.", "", "Clothing");
            testName.addToDonationList(donationTwo);
        } catch (IllegalArgumentException e) {
            assertEquals("Size of the donationList is NOT incremented", 1, testName.getDonationList().size());
        }

        try {
            DonationItem donationThree = new DonationItem("Clothes", "11/12/2018", addIn.getLocationName(),
                    "Clothes for donation.", "Various apparel for children.", "900", "Clothing");
            testName.addToDonationList(donationThree);
        } catch (IllegalArgumentException e) {
            assertEquals("Size of the donationList is NOT incremented", 1, testName.getDonationList().size());
        }

        try {
            DonationItem donationFour = new DonationItem("Clothes", "11/15/2018", "",
                    "Clothes for donation.", "Various apparel for children.", "300", "Clothing");
            testName.addToDonationList(donationFour);
        } catch (IllegalArgumentException e) {
            assertEquals("Size of the donationList is NOT incremented", 2, testName.getDonationList().size());
        }



    }



}
