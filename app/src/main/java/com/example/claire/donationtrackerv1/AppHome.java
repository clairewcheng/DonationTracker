package com.example.claire.donationtrackerv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppHome extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private DatabaseReference mLocationsRef;
    private ValueEventListener mUserListener;
    private ValueEventListener mLocationsListener;
    private User user;
    private ArrayList<Location> locations;

    private Button backbutton;
    private TextView userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home);

        backbutton = (Button) findViewById(R.id.tempsignoutbutton);
        userType = (TextView) findViewById(R.id.user_type_field);

        backbutton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("users").child(email.substring(0, email.indexOf(".")));
        mLocationsRef = FirebaseDatabase.getInstance().getReference().child("locations");
        locations = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the user
        // [START user_event_listener]
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get User object and use the values to update the UI
                user = dataSnapshot.getValue(User.class);
                if (user == null) {
                    Toast.makeText(AppHome.this, "Unable to retrieve user from database.", Toast.LENGTH_LONG).show();
                } else {
                    userType.setText(user.getUserType());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AppHome.this, "Failed to load user.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mUserRef.addValueEventListener(userListener);
        // [END user_event_listener]

        // Add value event listener to the locations
        // [START locations_event_listener]
        ValueEventListener locationsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Location objects and use the values to update the UI
                for (DataSnapshot locSnapshot: dataSnapshot.getChildren()) {
                    locations.add(locSnapshot.getValue(Location.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AppHome.this, "Failed to load locations.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mLocationsRef.addValueEventListener(locationsListener);
        // [END locations_event_listener]

        // Keep copy of listeners so we can remove them when app stops
        mUserListener = userListener;
        mLocationsListener = locationsListener;
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove user event listener
        if (mUserListener != null) {
            mUserRef.removeEventListener(mUserListener);
        }

        // Remove locations event listener
        if (mLocationsListener != null) {
            mLocationsRef.removeEventListener(mLocationsListener);
        }
    }

    @Override
    public void onClick(View view) {

        if (view == backbutton) {
            // navigate to logged in screen
            Intent intentLogOut = new Intent(getApplicationContext(),SignInScreen.class);
            startActivity(intentLogOut);
        }

    }
}
