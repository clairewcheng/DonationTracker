package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.claire.donationtrackerv1.R;

public class SearchAndFiltersActivity extends AppCompatActivity implements View.OnClickListener {

    // x button to return to previous screen
    private Button exitSearch;

    // search GO button to activate search-- will launch results activity
    private Button processSearch;

    // will hold the users search term to be passed to results
    private EditText searchTerm;

    //Locations quick jump filters will launch activity with particular location filter
    private ImageView Location1;
    private ImageView Location2;
    private ImageView Location3;
    private ImageView Location4;
    private ImageView Location5;
    private ImageView Location6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_filters);

        exitSearch = findViewById(R.id.exit_search);
        processSearch = findViewById(R.id.search_icon);
        searchTerm = findViewById(R.id.search_edit_text);
        Location1 = findViewById(R.id.location_image);
        Location2 = findViewById(R.id.location_image2);
        Location3 = findViewById(R.id.location_image3);
        Location4 = findViewById(R.id.location_image4);
        Location5 = findViewById(R.id.location_image5);
        Location6 = findViewById(R.id.location_image6);


        exitSearch.setOnClickListener(this);
        processSearch.setOnClickListener(this);
        Location1.setOnClickListener(this);
        Location2.setOnClickListener(this);
        Location3.setOnClickListener(this);
        Location4.setOnClickListener(this);
        Location5.setOnClickListener(this);
        Location6.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        //navigate back to previous screen if x icon is pressed
        if(view == exitSearch) {
            finish();
        }


        if(view == processSearch) {
            Intent intentGoToSearchResults = new Intent(this,
                    SearchResultsActivity.class);
            String searchTermToPass = searchTerm.getText().toString();
            intentGoToSearchResults.putExtra("searchTerm", searchTermToPass);
            startActivity(intentGoToSearchResults);
        }

        if(view == Location1) {
            Intent intentGoToLocation1Results = new Intent(this,
                    SearchResultsActivity.class);
            intentGoToLocation1Results.putExtra("location", "AFD Station 4");
            String searchTermToPass = searchTerm.getText().toString();
            intentGoToLocation1Results.putExtra("searchTerm", searchTermToPass);
            startActivity(intentGoToLocation1Results);
        }

        if(view == Location2) {
            Intent intentGoToLocation2Results = new Intent(this,
                    SearchResultsActivity.class);
            intentGoToLocation2Results.putExtra("location",
                    "BOYS & GIRLS CLUB W.W. WOOLFOLK");
            String searchTermToPass = searchTerm.getText().toString();
            intentGoToLocation2Results.putExtra("searchTerm", searchTermToPass);
            startActivity(intentGoToLocation2Results);
        }

        if(view == Location3) {
            Intent intentGoToLocation3Results = new Intent(this,
                    SearchResultsActivity.class);
            intentGoToLocation3Results.putExtra("location",
                    "PATHWAY UPPER ROOM CHRISTIAN MINISTRIES");
            String searchTermToPass = searchTerm.getText().toString();
            intentGoToLocation3Results.putExtra("searchTerm", searchTermToPass);
            startActivity(intentGoToLocation3Results);
        }

        if(view == Location4) {
            Intent intentGoToLocation4Results = new Intent(this,
                    SearchResultsActivity.class);
            intentGoToLocation4Results.putExtra("location",
                    "PAVILION OF HOPE INC");
            String searchTermToPass = searchTerm.getText().toString();
            intentGoToLocation4Results.putExtra("searchTerm", searchTermToPass);
            startActivity(intentGoToLocation4Results);
        }

        if(view == Location5) {
            Intent intentGoToLocation5Results = new Intent(this,
                    SearchResultsActivity.class);
            intentGoToLocation5Results.putExtra("location", "D&D CONVENIENCE STORE");
            String searchTermToPass = searchTerm.getText().toString();
            intentGoToLocation5Results.putExtra("searchTerm", searchTermToPass);
            startActivity(intentGoToLocation5Results);
        }

        if(view == Location6) {
            Intent intentGoToLocation6Results = new Intent(
                    this, SearchResultsActivity.class);
            intentGoToLocation6Results.putExtra("location",
                    "KEEP NORTH FULTON BEAUTIFUL");
            String searchTermToPass = searchTerm.getText().toString();
            intentGoToLocation6Results.putExtra("searchTerm", searchTermToPass);
            startActivity(intentGoToLocation6Results);
        }

    }

}
