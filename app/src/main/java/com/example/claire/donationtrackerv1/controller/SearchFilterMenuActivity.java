package com.example.claire.donationtrackerv1.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claire.donationtrackerv1.R;

/**
 * SearchFilterMenuActivity controls the search filters activity
 */
public class SearchFilterMenuActivity extends AppCompatActivity implements View.OnClickListener{


    //Navigation Buttons
    private Button returnButton;

    // Active Filters Text Fields
    private TextView categoryFilterTextView;


    //Category Filter Buttons
    private ImageView clothingFilterButton;
    private ImageView hatFilterButton;
    private ImageView householdFilterButton;
    private ImageView kitchenFilterButton;
    private ImageView electronicsFilterButton;
    private ImageView otherFilterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter_menu);

        returnButton = findViewById(R.id.back_button);

        // Edit active filters button
        Button removeFilterBubbleButton = findViewById(R.id.delete);

        categoryFilterTextView = (Button) findViewById(R.id.active_category_filter_bubble);

        clothingFilterButton = findViewById(R.id.clothing_button);
        hatFilterButton = findViewById(R.id.hat_button);
        householdFilterButton = findViewById(R.id.household_button);
        kitchenFilterButton = findViewById(R.id.kitchen_button);
        electronicsFilterButton = findViewById(R.id.electronics_button);
        otherFilterButton = findViewById(R.id.other_button);

        Button location1Toggle = findViewById(R.id.location_button);
        Button location2Toggle = findViewById(R.id.location_button2);
        Button location3Toggle = findViewById(R.id.location_button3);
        Button location4Toggle = findViewById(R.id.location_button4);
        Button location5Toggle = findViewById(R.id.location_button5);
        Button location6Toggle = findViewById(R.id.location_button6);
        Button location7Toggle = findViewById(R.id.location_button7);
        Button location8Toggle = findViewById(R.id.location_button8);

        returnButton.setOnClickListener(this);
        removeFilterBubbleButton.setOnClickListener(this);
        clothingFilterButton.setOnClickListener(this);
        hatFilterButton.setOnClickListener(this);
        householdFilterButton.setOnClickListener(this);
        kitchenFilterButton.setOnClickListener(this);
        electronicsFilterButton.setOnClickListener(this);
        otherFilterButton.setOnClickListener(this);
        location1Toggle.setOnClickListener(this);
        location2Toggle.setOnClickListener(this);
        location3Toggle.setOnClickListener(this);
        location4Toggle.setOnClickListener(this);
        location5Toggle.setOnClickListener(this);
        location6Toggle.setOnClickListener(this);
        location7Toggle.setOnClickListener(this);
        location8Toggle.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view == returnButton) {
            finish();
        }

        if(view == clothingFilterButton) {
            if(clothingFilterButton.isActivated()){
                clothingFilterButton.setActivated(false);
                categoryFilterTextView.setText("No Category Filter Active");
            } else {
                clothingFilterButton.setActivated(true);
                categoryFilterTextView.setText("Clothing");
            }

        }

        if(view == hatFilterButton) {
            if(hatFilterButton.isActivated()){
                hatFilterButton.setActivated(false);
                categoryFilterTextView.setText("No Category Filter Active");
            } else {
                hatFilterButton.setActivated(true);
                categoryFilterTextView.setText("Hats");
            }
        }

        if(view == householdFilterButton) {
            if(householdFilterButton.isActivated()){
                householdFilterButton.setActivated(false);
                categoryFilterTextView.setText("No Category Filter Active");
            } else {
                householdFilterButton.setActivated(true);
                categoryFilterTextView.setText("Household");
            }
        }

        if(view == kitchenFilterButton) {
            if(kitchenFilterButton.isActivated()){
                kitchenFilterButton.setActivated(false);
                categoryFilterTextView.setText("No Category Filter Active");
            } else {
                kitchenFilterButton.setActivated(true);
                categoryFilterTextView.setText("Kitchen");
            }
        }

        if(view == electronicsFilterButton) {
            if(electronicsFilterButton.isActivated()){
                electronicsFilterButton.setActivated(false);
                categoryFilterTextView.setText("No Category Filter Active");
            } else {
                electronicsFilterButton.setActivated(true);
                categoryFilterTextView.setText("Electronics");
            }
        }

        if(view == otherFilterButton) {
            if(otherFilterButton.isActivated()){
                otherFilterButton.setActivated(false);
                categoryFilterTextView.setText("No Category Filter Active");
            } else {
                otherFilterButton.setActivated(true);
                categoryFilterTextView.setText("Other");
            }
        }



    }
}
