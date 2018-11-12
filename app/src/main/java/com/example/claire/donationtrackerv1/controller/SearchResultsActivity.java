package com.example.claire.donationtrackerv1.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claire.donationtrackerv1.R;
import com.example.claire.donationtrackerv1.model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity implements View.OnClickListener {

    //exit search button X in top left corner
    private Button exitSearch;

    // Search edit text is where user types search terms
    private EditText searchTermEdit;

    //refresh search button/search GO button
    private Button processSearch;

    //Current Location defined on previous screen. Default is All location results
    private TextView locationIndicatorText;

    //Category Filter Toggles on and off only one on at a time
    private ImageView clothingFilterButton;
    private ImageView hatFilterButton;
    private ImageView householdFilterButton;
    private ImageView kitchenFilterButton;
    private ImageView electronicsFilterButton;
    private ImageView otherFilterButton;

    private DatabaseReference mItemsRef;
    private ValueEventListener mItemsListener;
    private java.util.List<Item> allItems;
    private java.util.List<Item> results;
    private String searchTerm;
    private String category;
    private String locationName;
    private RecyclerView mItemsRecyclerView;
    private ItemsRVAdapter mItemsAdapter;
    private RecyclerView.LayoutManager mItemsLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        exitSearch = (Button) findViewById(R.id.exit_button);
        searchTermEdit = (EditText) findViewById(R.id.search_edit_text);
        processSearch = (Button) findViewById(R.id.search_icon);
        locationIndicatorText = (TextView) findViewById(R.id.location_filter_label);
        clothingFilterButton = (ImageView) findViewById(R.id.clothing_button);
        hatFilterButton = (ImageView) findViewById(R.id.hat_button);
        householdFilterButton = (ImageView) findViewById(R.id.household_button);
        kitchenFilterButton = (ImageView) findViewById(R.id.kitchen_button);
        electronicsFilterButton = (ImageView) findViewById(R.id.electronics_button);
        otherFilterButton = (ImageView) findViewById(R.id.other_button);

        exitSearch.setOnClickListener(this);
        processSearch.setOnClickListener(this);
        clothingFilterButton.setOnClickListener(this);
        hatFilterButton.setOnClickListener(this);
        householdFilterButton.setOnClickListener(this);
        kitchenFilterButton.setOnClickListener(this);
        electronicsFilterButton.setOnClickListener(this);
        otherFilterButton.setOnClickListener(this);

        Intent intent = getIntent();
        searchTerm = intent.getStringExtra("searchTerm");
        if (searchTerm.equals("")) {
            searchTerm = null;
        }
        locationName = intent.getStringExtra("location");
        mItemsRef = FirebaseDatabase.getInstance().getReference().child("items");

        if (locationName != null) {
            locationIndicatorText.setText(locationName);
        }


        mItemsRecyclerView = (RecyclerView) findViewById(R.id.itemresultsrecyclerview);
        mItemsLayoutManager = new LinearLayoutManager(this);
        mItemsRecyclerView.setLayoutManager(mItemsLayoutManager);

        //Add items event listener to the items list
        ValueEventListener itemsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allItems = new ArrayList<>();
                //Get items objects and update values
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    allItems.add(itemSnapshot.getValue(Item.class));
                }
                filterResults();
                mItemsAdapter = new ItemsRVAdapter(getApplicationContext(), results);
                mItemsRecyclerView.setAdapter(mItemsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SearchResultsActivity.this, "Failed to load items.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mItemsRef.addValueEventListener(itemsListener);
        mItemsListener = itemsListener;
    }

    private void filterResults() {
        results = new ArrayList<>();
        for (Item i: allItems) {
            if (searchTerm != null && category != null && locationName != null) {
                if ((i.getShortDesc().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        searchTerm.toLowerCase().contains(i.getShortDesc().toLowerCase())) &&
                        i.getCategory().equals(category) && i.getLocation().equals(locationName)) {
                    results.add(i);
                }
            } else if (searchTerm != null && category != null) {
                if ((i.getShortDesc().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        searchTerm.toLowerCase().contains(i.getShortDesc().toLowerCase()))
                        && i.getCategory().equals(category)) {
                    results.add(i);
                }
            } else if (searchTerm != null && locationName != null) {
                if ((i.getShortDesc().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        searchTerm.toLowerCase().contains(i.getShortDesc().toLowerCase()))
                        && i.getLocation().equals(locationName)) {
                    results.add(i);
                }
            } else if (category != null && locationName != null) {
                if (i.getCategory().equals(category) && i.getLocation().equals(locationName)) {
                    results.add(i);
                }
            } else if ((searchTerm != null &&
                    (i.getShortDesc().toLowerCase().contains(searchTerm.toLowerCase()) ||
                            searchTerm.toLowerCase().contains(i.getShortDesc().toLowerCase())))
                    || (category != null && i.getCategory().equals(category))
                    || (locationName != null && i.getLocation().equals(locationName))) {
                results.add(i);
            } else if (searchTerm == null && category == null && locationName == null){
                results.add(i);
            }
        }
    }

    private void updateRV() {
        filterResults();
        mItemsAdapter.refreshItems(results);
    }

    //method that clears all selected categories
    private void clearSelectedCategoryFilters() {
        clothingFilterButton.setActivated(false);
        hatFilterButton.setActivated(false);
        householdFilterButton.setActivated(false);
        kitchenFilterButton.setActivated(false);
        electronicsFilterButton.setActivated(false);
        otherFilterButton.setActivated(false);
    }

    // method that a text view can call that will clear the other category selected and
    // make the new category selected the active filter stored
    private void updateActiveCategoryFilter(ImageView imageView, String category) {

        if(!imageView.isActivated()) {
            clearSelectedCategoryFilters();
            imageView.setActivated(true);
            this.category = category;
        } else {
            clearSelectedCategoryFilters();
            this.category = null;
        }
        updateRV();
    }

    @Override
    public void onClick(View view) {

        if(view == exitSearch) {
            finish();
        }

        if(view == processSearch) {
            //update the recycler view and update the value of the query
            searchTerm = searchTermEdit.getText().toString();
            if (searchTerm.equals("")) {
                searchTerm = null;
            }
            updateRV();
        }

        if(view == clothingFilterButton) {
            updateActiveCategoryFilter(clothingFilterButton, "Clothing");
        }

        if(view == hatFilterButton) {
            updateActiveCategoryFilter(hatFilterButton, "Hat");
        }

        if(view == householdFilterButton) {
            updateActiveCategoryFilter(householdFilterButton, "Household");
        }

        if(view == kitchenFilterButton) {
            updateActiveCategoryFilter(kitchenFilterButton, "Kitchen");
        }

        if(view == electronicsFilterButton) {
            updateActiveCategoryFilter(electronicsFilterButton, "Electronics");
        }

        if(view == otherFilterButton) {
            updateActiveCategoryFilter(otherFilterButton, "Other");
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove items event listener
        if(mItemsListener != null) {
            mItemsRef.removeEventListener(mItemsListener);
        }
    }
}
