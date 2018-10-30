package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claire.donationtrackerv1.R;

public class SearchAndFiltersActivity extends AppCompatActivity implements View.OnClickListener {

    // Basic navigation buttons
    private Button exitSearch;
    private Button processSearch;
    private Button addFilters;
    private EditText queryEditText;

    // Explore category navigation buttons(go to category)
    private Button clothingCategoryButton;
    private Button hatsCategoryButton;
    private Button householdCategoryButton;
    private Button kitchenCategoryButton;
    private Button electronicsCategoryButton;
    private Button otherCategoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_filters);

        // Basic navigation buttons view id
        exitSearch = (Button)  findViewById(R.id.exit_search);
        processSearch = (Button) findViewById(R.id.search_icon);
        addFilters = (Button) findViewById(R.id.add_filter_button);

        // Basic navigation buttons set listeners
        exitSearch.setOnClickListener(this);
        processSearch.setOnClickListener(this);
        addFilters.setOnClickListener(this);

        // Explore category navigation buttons(go to category) view by id
        clothingCategoryButton = (Button) findViewById(R.id.clothing_button);
        hatsCategoryButton = (Button) findViewById(R.id.hat_button);
        householdCategoryButton = (Button) findViewById(R.id.household_button);
        kitchenCategoryButton = (Button) findViewById(R.id.kitchen_button);
        electronicsCategoryButton = (Button) findViewById(R.id.electronics_button);
        otherCategoryButton = (Button) findViewById(R.id.other_button);

        clothingCategoryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //navigate back to previous screen if x icon is pressed
        if(view == exitSearch) {
            finish();
        }

        if(view == addFilters) {
            Intent intentGoToFiltersMenu = new Intent(getApplicationContext(), SearchFilterMenuActivity.class);
            startActivity(intentGoToFiltersMenu);
        }

        if(view == clothingCategoryButton) {
            Intent intentGoToClothingResults = new Intent(this, SearchResultsActivity.class);
            intentGoToClothingResults.putExtra("category", "Clothing");
            startActivity(intentGoToClothingResults);
        }

    }

}
