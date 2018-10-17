package com.example.claire.donationtrackerv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LocationDetailActivity extends AppCompatActivity {

    private DatabaseReference mLocRef;
    private ValueEventListener mLocListener;
    private Location location;

    private TextView locInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail);
        locInfo = (TextView) findViewById(R.id.loc_info);

        Intent intent = getIntent();
        String locIndex = intent.getStringExtra("locationID");
        mLocRef = FirebaseDatabase.getInstance().getReference().child("locations").child(locIndex);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the location
        // [START location_event_listener]
        ValueEventListener locListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Location object and use the values to update the UI
                location = dataSnapshot.getValue(Location.class);
                if (location == null) {
                    Toast.makeText(LocationDetailActivity.this,
                            "Unable to retrieve location from database.",
                            Toast.LENGTH_LONG).show();
                } else {
                    locInfo.setText(location.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LocationDetailActivity.this, "Failed to load location.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mLocRef.addValueEventListener(locListener);
        // [END location_event_listener]

        // Keep copy of listener so we can remove it when app stops
        mLocListener = locListener;
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove location event listener
        if (mLocListener != null) {
            mLocRef.removeEventListener(mLocListener);
        }
    }
}
