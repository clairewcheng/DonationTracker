package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Item;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class AddItem extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mDatabase;

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

        //TODO: change this to pull locations from Firebase
        List<String> _locations = Arrays.asList("Loc 1", "Loc 2", "Loc 3", "Loc 4", "Loc 5");
        ArrayAdapter<String> locAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, _locations);
        locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locAdapter);

        addItemButton.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void createItem() {
        String _shortDesc = shortDescField.getText().toString().trim();
        String _longDesc = longDescField.getText().toString().trim();
        String _value = valueField.getText().toString().trim();
        String _time = timeField.getText().toString().trim();
        String _date = dateField.getText().toString().trim();
        String _comment = commentField.getText().toString().trim();
        String _location = (String) locationSpinner.getSelectedItem();
        String _category = (String) categorySpinner.getSelectedItem();

        _item = new Item(_shortDesc, _longDesc, _value, _time, _date, _comment, _location, _category);
        //TODO: change to add by unique ids
        mDatabase.child("items").child("ID:" + _shortDesc).setValue(_item);

        Intent intentHome = new Intent(getApplicationContext(),AppHome.class);
        startActivity(intentHome);
    }

    @Override
    public void onClick(View view) {
        if (view == addItemButton) {
            createItem();
        }
    }
}
