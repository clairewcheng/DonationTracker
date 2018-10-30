package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claire.donationtrackerv1.R;

public class SearchFilterMenuActivity extends AppCompatActivity implements View.OnClickListener{


    //Navigation Buttons
    private Button returnButton;

    //Edit Active Filters Buttons
    private Button removeFilterBubbleButton;

    // Active Filters Text Fields
    private TextView categoryFilterTextView;


    //Category Filter Buttons
    private ImageView clothingFilterButton;
    private ImageView hatFilterButton;
    private ImageView householdFilterButton;
    private ImageView kitchenFilterButton;
    private ImageView electronicsFilterButton;
    private ImageView otherFilterButton;

    //Locations Filter Button
    private Button location1Toggle;
    private Button location2Toggle;
    private Button location3Toggle;
    private Button location4Toggle;
    private Button location5Toggle;
    private Button location6Toggle;
    private Button location7Toggle;
    private Button location8Toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter_menu);

        returnButton = (Button) findViewById(R.id.back_button);

        removeFilterBubbleButton = (Button) findViewById(R.id.delete);

        categoryFilterTextView = (Button) findViewById(R.id.active_category_filter_bubble);

        clothingFilterButton = (ImageView) findViewById(R.id.clothing_button);
        hatFilterButton = (ImageView) findViewById(R.id.hat_button);
        householdFilterButton = (ImageView) findViewById(R.id.household_button);
        kitchenFilterButton = (ImageView) findViewById(R.id.kitchen_button);
        electronicsFilterButton = (ImageView) findViewById(R.id.electronics_button);
        otherFilterButton = (ImageView) findViewById(R.id.other_button);

        location1Toggle = (Button) findViewById(R.id.location_button);
        location2Toggle = (Button) findViewById(R.id.location_button2);
        location3Toggle = (Button) findViewById(R.id.location_button3);
        location4Toggle = (Button) findViewById(R.id.location_button4);
        location5Toggle = (Button) findViewById(R.id.location_button5);
        location6Toggle = (Button) findViewById(R.id.location_button6);
        location7Toggle = (Button) findViewById(R.id.location_button7);
        location8Toggle = (Button) findViewById(R.id.location_button8);

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
