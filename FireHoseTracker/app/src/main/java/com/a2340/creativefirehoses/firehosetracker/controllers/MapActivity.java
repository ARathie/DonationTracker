package com.a2340.creativefirehoses.firehosetracker.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.a2340.creativefirehoses.firehosetracker.R;
import com.a2340.creativefirehoses.firehosetracker.model.LocationItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.a2340.creativefirehoses.firehosetracker.model.LocationModel;

import java.util.List;
import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * @param googleMap map for google
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LocationModel model = LocationModel.INSTANCE;
        List<LatLng> coordinates = new ArrayList<>();
        for (LocationItem location: model.getLocations()) {
            double latitude = Double.parseDouble(location.getLatitude());
            double longitude = Double.parseDouble(location.getLongitude());
            /*String text = "";
            text += location.getLocationName() + "\n";
            text += location.getPhoneNum() + "\n";*/
            LatLng coordinate = new LatLng(latitude, longitude);
            coordinates.add(coordinate);
            mMap.addMarker(new MarkerOptions().position(coordinate).title(location.getLocationName()).snippet(location.getPhoneNum()));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates.get(0), 12.0f));
    }
}
