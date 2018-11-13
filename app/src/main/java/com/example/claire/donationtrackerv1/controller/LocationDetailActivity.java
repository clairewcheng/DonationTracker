package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Item;
import com.example.claire.donationtrackerv1.model.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * LocationDetailActivity controls the screen with the location details after clicking on the
 * general location cards
 */
public class LocationDetailActivity extends AppCompatActivity {

    private DatabaseReference mLocRef;
    private ValueEventListener mLocListener;

    private DatabaseReference mItemsRef;
    private ValueEventListener mItemsListener;
    private ArrayList<Item> items;
    private Location location;

    private TextView locInfo;
    private TextView locationName;
    private RecyclerView mItemsRecyclerView;
    private ItemsRVAdapter mItemsAdapter;
    private Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        locInfo = findViewById(R.id.loc_info);
        locationName = findViewById(R.id.location_name);

        Intent intent = getIntent();
        String locIndex = intent.getStringExtra("locationID");
        mLocRef = FirebaseDatabase.getInstance().getReference().child("locations").child(locIndex);
        mItemsRef = FirebaseDatabase.getInstance().getReference().child("items");

        mItemsRecyclerView = findViewById(R.id.itemListRecyclerView);
        RecyclerView.LayoutManager mItemsLayoutManager = new LinearLayoutManager(this);
        mItemsRecyclerView.setLayoutManager(mItemsLayoutManager);

        // Add value event listener to the location
        // [START location_event_listener]
        ValueEventListener locListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Location object and use the values to update the UI
                location = dataSnapshot.getValue(Location.class);
                query = mItemsRef.orderByChild("location").equalTo(location.getName());
                query.addListenerForSingleValueEvent(mItemsListener);

                if (location == null) {
                    Toast toast = Toast.makeText(LocationDetailActivity.this,
                            "Unable to retrieve location from database.",
                            Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    locInfo.setText(location.toString());
                    locationName.setText(location.getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(LocationDetailActivity.this, "Failed to load location.",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        mLocRef.addValueEventListener(locListener);
        // [END location_event_listener]

        // Keep copy of listener so we can remove it when app stops
        mLocListener = locListener;

        //Add items event listener to the items list
        ValueEventListener itemsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items = new ArrayList<>();
                //Get items objects and update values
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    items.add(itemSnapshot.getValue(Item.class));
                }
                mItemsAdapter = new ItemsRVAdapter(getApplicationContext(), items);
                mItemsRecyclerView.setAdapter(mItemsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(LocationDetailActivity.this, "Failed to load items.",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        mItemsRef.addValueEventListener(itemsListener);
        mItemsListener = itemsListener;
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove location event listener
        if (mLocListener != null) {
            mLocRef.removeEventListener(mLocListener);
        }

        if(mItemsListener != null) {
            mItemsRef.removeEventListener(mItemsListener);
        }
    }
}
