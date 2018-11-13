package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.annotation.NonNull;

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Item;
import com.example.claire.donationtrackerv1.model.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * AddItemActivity controls the Add Item Screen so that users may enter a donation item's info
 * and store it to the database for later view
 */
public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mDatabase;
    private DatabaseReference mLocationsRef;
    private ValueEventListener mLocationsListener;

    private EditText shortDescField;
    private EditText longDescField;
    private EditText valueField;
    private EditText timeField;
    private EditText dateField;
    private EditText commentField;
    private Button addItemButton;
    private Spinner categorySpinner;
    private Spinner locationSpinner;

    private ArrayList<String> locationNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        shortDescField = findViewById(R.id.shortDescriptionEntry);
        longDescField = findViewById(R.id.fullDescriptionEntry);
        valueField = findViewById(R.id.valueEntry);
        timeField = findViewById(R.id.timeOfDropOffTimeEntry);
        dateField = findViewById(R.id.timeOfDropOffDateEntry);
        commentField = findViewById(R.id.commentsEntry);
        addItemButton = findViewById(R.id.addItemButton);
        categorySpinner = findViewById(R.id.categorySpinner);
        locationSpinner = findViewById(R.id.locationSpinner);

        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Item._category);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(catAdapter);

        mLocationsRef = FirebaseDatabase.getInstance().getReference().child("locations");

        // Add value event listener to the locations
        ValueEventListener locationsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Empty locations list
                locationNames = new ArrayList<>();
                // Get Location objects and use the values to update the UI
                for (DataSnapshot locSnapshot: dataSnapshot.getChildren()) {
                    Location l = locSnapshot.getValue(Location.class);
                    if (l != null) {
                        locationNames.add(l.getName());
                    }
                }
                ArrayAdapter<String> locAdapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, locationNames);
                locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                locationSpinner.setAdapter(locAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(AddItemActivity.this,
                        "Failed to load locations.", Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        mLocationsRef.addValueEventListener(locationsListener);
        // [END locations_event_listener]
        // mLocationsListener = locationsListener;
        mLocationsListener = locationsListener;

        addItemButton.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void createItem() {
        // Short description
        android.text.Editable input = shortDescField.getText();
        String inputString = input.toString();
        String _shortDesc = inputString.trim();
        // Long description
        input = longDescField.getText();
        inputString = input.toString();
        String _longDesc = inputString.trim();
        // Value
        input = valueField.getText();
        inputString = input.toString();
        String _value = inputString.trim();
        // Time
        input = timeField.getText();
        inputString = input.toString();
        String _time = inputString.trim();
        // Date
        input = dateField.getText();
        inputString = input.toString();
        String _date = inputString.trim();
        // Comment
        input = commentField.getText();
        inputString = input.toString();
        String _comment = inputString.trim();
        // Location
        String _location = (String) locationSpinner.getSelectedItem();
        // Category
        String _category = (String) categorySpinner.getSelectedItem();

        Item _item = new Item(_shortDesc,
                _longDesc,
                _value,
                _time,
                _date,
                _comment,
                _location,
                _category);
        // (currently name but paths can't contain . or # or $ or [ or ]
        mDatabase.child("items").child("ID:" + _shortDesc).setValue(_item);

        Intent intentHome = new Intent(getApplicationContext(),AppHomeActivity.class);
        startActivity(intentHome);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Remove locations event listener
        if (mLocationsListener != null) {
            mLocationsRef.removeEventListener(mLocationsListener);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == addItemButton) {
            createItem();
        }
    }
}
