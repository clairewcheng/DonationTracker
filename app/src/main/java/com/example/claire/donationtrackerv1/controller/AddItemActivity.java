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

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Item;
import com.example.claire.donationtrackerv1.model.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

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
    //TODO: add picture capability

    private Item _item;
    private ArrayList<String> locationNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        shortDescField = (EditText) findViewById(R.id.shortDescriptionEntry);
        longDescField = (EditText) findViewById(R.id.fullDescriptionEntry);
        valueField = (EditText) findViewById(R.id.valueEntry);
        timeField = (EditText) findViewById(R.id.timeOfDropoffTimeEntry);
        dateField = (EditText) findViewById(R.id.timeOfDropoffDateEntry);
        commentField = (EditText) findViewById(R.id.commentsEntry);
        addItemButton = (Button) findViewById(R.id.addItemButton);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);

        ArrayAdapter<String> catAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Item._category);
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
                    locationNames.add(locSnapshot.getValue(Location.class).getName());
                }
                ArrayAdapter<String> locAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, locationNames);
                locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                locationSpinner.setAdapter(locAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddItemActivity.this, "Failed to load locations.", Toast.LENGTH_SHORT).show();
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
        boolean validInput = true;
        String _shortDesc = shortDescField.getText().toString().trim();
        if ("".equals(_shortDesc) || _shortDesc.contains(".") || _shortDesc.contains("#")
                || _shortDesc.contains("$") || _shortDesc.contains("[") || _shortDesc.contains("]")) {
            Toast.makeText(getApplicationContext(), "Please Enter Valid Short Description", Toast.LENGTH_SHORT).show();
            validInput = false;
        }
        String _longDesc = longDescField.getText().toString().trim();
        if ("".equals(_longDesc)) {
            Toast.makeText(getApplicationContext(), "Please Enter Full Description", Toast.LENGTH_SHORT).show();
            validInput = false;
        }
        String _value = valueField.getText().toString().trim();
        if (_value.contains("$")) {
            _value = _value.substring(1);
        }
        try {
            Double value = Double.valueOf(_value);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please Enter Valid Value", Toast.LENGTH_SHORT).show();
            validInput = false;
        }
        String _time = timeField.getText().toString().trim();
        if ((!_time.contains("am") && !_time.contains("pm")) || !_time.contains(":")) {
            Toast.makeText(getApplicationContext(), "Please Enter Valid Time", Toast.LENGTH_SHORT).show();
            validInput = false;
        }
        String _date = dateField.getText().toString().trim();
        ArrayList<String> months = new ArrayList<>(Arrays.asList("january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"));
        if (_date.contains(" ") && !months.contains(_date.substring(0, _date.indexOf(" ")).toLowerCase())) {
            Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_SHORT).show();
            validInput = false;
        }
        String _comment = commentField.getText().toString().trim();
        String _location = (String) locationSpinner.getSelectedItem();
        String _category = (String) categorySpinner.getSelectedItem();

        if (validInput) {
            _item = new Item(_shortDesc, _longDesc, _value, _time, _date, _comment, _location, _category);
            //TODO: change to add by unique ids, currently using shortDesc, new items with same desc replace old data
            mDatabase.child("items").child("ID:" + _shortDesc).setValue(_item);

            Intent intentHome = new Intent(getApplicationContext(), AppHomeActivity.class);
            intentHome.putExtra("add", "Add Item Successful");
            //Toast.makeText(getApplicationContext(), "Add Item Successful", Toast.LENGTH_SHORT).show();
            startActivity(intentHome);
        }
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
