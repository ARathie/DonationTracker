package com.a2340.creativefirehoses.firehosetracker;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single DataItem detail screen.
 * This fragment is either contained in a {@link LocationItemListActivity}
 * in two-pane mode (on tablets) or a {@link LocationItemDetailActivity}
 * on handsets.
 */
public class LocationItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private LocationItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LocationItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //int item_id = getArguments().getInt(ARG_ITEM_ID);
            Log.d("MYAPP", "Start details for: " + "item_id");
            mItem = LocationModel.INSTANCE.findItemByName("item_id");

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getLocationName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.locationitem_detail, container, false);
        Log.d("MYAPP", "Getting ready to set data");
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            Log.d("MYAPP", "Getting ready to set name");
            ((TextView) rootView.findViewById(R.id.locationName)).setText(mItem.getLocationName());
            ((TextView) rootView.findViewById(R.id.latitude)).setText("" + mItem.getLatitude());
            ((TextView) rootView.findViewById(R.id.longitude)).setText("" +mItem.getLongitude());
            ((TextView) rootView.findViewById(R.id.streetAddress)).setText(mItem.getStreetAddress());
            ((TextView) rootView.findViewById(R.id.city)).setText(mItem.getCity());
            ((TextView) rootView.findViewById(R.id.state)).setText(mItem.getState());
            ((TextView) rootView.findViewById(R.id.zip)).setText(mItem.getZip());
            ((TextView) rootView.findViewById(R.id.type)).setText(mItem.getType());
            ((TextView) rootView.findViewById(R.id.phoneNum)).setText(mItem.getPhoneNum());
            ((TextView) rootView.findViewById(R.id.website)).setText(mItem.getWebsite());
        }

        return rootView;
    }
}
