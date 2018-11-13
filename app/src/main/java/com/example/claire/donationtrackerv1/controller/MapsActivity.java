package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claire.donationtrackerv1.model.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.claire.donationtrackerv1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private DatabaseReference mLocationsRef;
    private java.util.List<Location> locations;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ValueEventListener mLocationsListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLocationsRef = FirebaseDatabase.getInstance().getReference().child("locations");
        super.onCreate(savedInstanceState);

        ValueEventListener locationsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Empty arrayList
                locations = new ArrayList<>();
                // Get Location objects and use the values to update the UI
                for (DataSnapshot locSnapshot: dataSnapshot.getChildren()) {
                    locations.add(locSnapshot.getValue(Location.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MapsActivity.this,
                        "Failed to load locations.", Toast.LENGTH_SHORT).show();
            }
        };
        mLocationsRef.addValueEventListener(locationsListener);
        // [END locations_event_listener]
        // mLocationsListener = locationsListener;
        mLocationsListener = locationsListener;
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        // Add value event listener to the locations
    }

    public  class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.MyViewHolder> {
        private final java.util.List<Location> mDataSet;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mContentView;
            Location mLocation;

            MyViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = view.findViewById(R.id.content);
            }
        }

        public MapsAdapter(java.util.List<Location> myDataSet) {
            mDataSet = myDataSet;
        }

        @Override
        public MapsAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            //create new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.location_card_rv, parent, false);
            return  new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MapsAdapter.MyViewHolder holder, final int position) {
            // - get element from your data set at this position
            // - replace the contents of the view with that element
            holder.mLocation = mDataSet.get(position);
            String text = mDataSet.get(position).getName()
                    + "\n" + mDataSet.get(position).getPhone();
            holder.mContentView.setText(text);

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //on a phone, we need to change windows to the detail view
                    //create our new intent with the new screen (activity)
                    Intent intent = new Intent(getApplicationContext(),
                            LocationDetailActivity.class);
                /*
                    pass along the id of the location so we can retrieve the correct data in
                    the next window
                 */
                    intent.putExtra("locationID", "" + position);

                    //now just display the new window
                    startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() {
            return mDataSet.size();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (!locations.isEmpty()) {
            LatLng start = new LatLng(Float.valueOf(locations.get(0).getLatitude()),
                    Float.valueOf(locations.get(0).getLongitude()));
            mMap.addMarker(new MarkerOptions().position(start)
                    .title(locations.get(0).getName()).snippet(locations.get(0).getPhone()));
            for ( int i = 1; i < locations.size(); i++ ) {
                float latitude = Float.valueOf(locations.get(i).getLatitude());
                float longitude = Float.valueOf(locations.get(i).getLongitude());
                LatLng temp = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(temp)
                        .title(locations.get(i).getName()).snippet(locations.get(i).getPhone()));
            }

            // Add a marker in Sydney and move the camera

            mMap.moveCamera(CameraUpdateFactory.newLatLng(start));
        }
    }


}
